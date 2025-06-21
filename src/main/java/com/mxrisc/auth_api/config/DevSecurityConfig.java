package com.mxrisc.auth_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("dev")
public class DevSecurityConfig {

   private final JwtAuthenticationConverter jwtAuthenticationConverter;

   public DevSecurityConfig(JwtAuthenticationConverter jwtAuthenticationConverter) {
      this.jwtAuthenticationConverter = jwtAuthenticationConverter;
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/sign").hasAuthority("ROLE_SIGNER")
                  .requestMatchers("/me").authenticated()
                  .anyRequest().permitAll())
            .oauth2ResourceServer(oauth2 -> oauth2
                  .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter)));
      return http.build();
   }
}
