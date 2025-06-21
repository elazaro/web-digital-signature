package com.mxrisc.auth_api.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

   @GetMapping("/me")
   public Map<String, Object> me(Authentication authentication) {
      Jwt jwt = (Jwt)authentication.getPrincipal();
      return Map.of(
            "username", jwt.getClaimAsString("preferred_username"),
            "email", jwt.getClaimAsString("email"),
            "name", jwt.getClaimAsString("name"),
            "roles", jwt.getClaimAsMap("realm_access").get("roles"),
            "all_claims", jwt.getClaims());
   }
}
