package com.mxrisc.auth_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Note: Fri Jun 20 15:59:16 CST 2025 - Somehow @Configuration along with
// @ConfigurationProperties causes sprintg to duplicate the bean when using
// EnableConfigurationProperties in the application, let's change this for no.
// @Configuration
// @ConfigurationProperties(prefix = "signer.keystore")
@ConfigurationProperties(prefix = "signer.keystore")
public class SignerProperties {

   private String path;
   private String password;
   private String alias;
   
   public String getPath() {
      return path;
   }
   public void setPath(String path) {
      this.path = path;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getAlias() {
      return alias;
   }
   public void setAlias(String alias) {
      this.alias = alias;
   }

}
