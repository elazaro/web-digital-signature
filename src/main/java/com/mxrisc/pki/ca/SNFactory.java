package com.mxrisc.pki.ca;

import com.mxrisc.pki.ca.api.SNHandler;
import com.mxrisc.pki.ca.config.SNHandlerProperties;

public class SNFactory {

   public static SNHandler createSerialNumberHandler(Class snhandlerclass) {
      SNHandler snhandler = null;
      try {
         if(snhandler == null) {
            snhandler = (SNHandler)snhandlerclass.newInstance();
         } else {
            //log as trace
         }
      } catch(InstantiationException ie) {
         ie.printStackTrace();
      } finally {
         return snhandler;
      }
   }

}
