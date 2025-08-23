package com.mxrisc.pki.ca.impl;

import com.mxrisc.pki.ca.config.SNHandlerProperties;
import com.mxrisc.pki.ca.api.SNHandler;
import java.io.FileInputStream;
import java.math.BigInteger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSonSNHandler implements SNHandler {
   private static final Logger log = LoggerFactory.getLogger("com.mxrisc");

   public void init(SNHandlerProperties props) {
      String registryPath = props.getRegistryPath();
      if(log.isDebugEnabled()) log.debug(String.format("Initiating serial number registry from %s json file.", registryPath));
      try(FileInputStream fis = new FileInputStream(registryPath)) {
         log.info("JsonSNHandler Initiated");
      } catch(Exception ex) {
         ex.printStackTrace();
      }
   }

   public BigInteger lockNextSn() {
      return BigInteger.ONE;
   } 
   public BigInteger getNextSn() {
      return this.lockNextSn();
   } 
   public BigInteger popLastSn() {
      return null;
   }
   public void commit() throws Exception {
      log.info("Persisting last serial number generated");
   }

}
