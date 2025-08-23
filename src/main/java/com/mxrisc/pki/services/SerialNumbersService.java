package com.mxrisc.pki.services;

import com.mxrisc.pki.ca.SNFactory;
import com.mxrisc.pki.ca.api.SNHandler;
import com.mxrisc.pki.ca.impl.JSonSNHandler;
import com.mxrisc.pki.ca.config.SNHandlerProperties;

import org.springframework.stereotype.Service;

@Service
public class SerialNumbersService {

   private final SNHandler snhandler;

   public SerialNumbersService(SNHandlerProperties props) throws Exception {
      snhandler = (SNHandler)SNFactory.createSerialNumberHandler(JSonSNHandler.class);
      snhandler.init(props);
   }

   public String getNextNumber() {
      return snhandler.getNextSn().toString();
   }
}
