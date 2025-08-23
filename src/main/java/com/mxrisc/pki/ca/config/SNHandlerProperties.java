package com.mxrisc.pki.ca.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// Note: Fri Aug 22 12:35:03 CST 2025 - Changing the strategy to use a factory
// to instantiate an SNHandler class.
// Note: Sun Aug 17 00:06:50 CST 2025 - Spring managed bean for storing signer
@ConfigurationProperties(prefix = "pki.ca.snhandler")
public class SNHandlerProperties {

   private String registryPath;

   public String getRegistryPath() {
      return registryPath;
   }

   public void setRegistryPath(String registryPath) {
      this.registryPath = registryPath;
   }

}
