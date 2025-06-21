package com.mxrisc.auth_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Note: This is Configuration bean injected by spring, I didn't noticed this
// before 🤦🏽‍♂️
@Configuration
public class JwtConfig {

   @Bean
   public JwtAuthenticationConverter jwtAuthenticationConverter() {
      var converter = new JwtAuthenticationConverter();
      converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
      return converter;
   }

   // Note: Fri Jun 20 19:49:36 CST 2025 - Ok... what's this <> syntax, I
   // didn't know about it. it is worth to investigate about it.
   static class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

      @Override
      public Collection<GrantedAuthority> convert(Jwt jwt) {
         var realmAccess = (Map<String, Object>)jwt.getClaims().get("realm_access");
         if(realmAccess == null || realmAccess.get("roles") == null) 
            return List.of();

         var roles = (List<String>)realmAccess.get("roles");
         return roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
      }
   }
}

