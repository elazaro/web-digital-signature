package com.mxrisc.pki.ca.api;

import com.mxrisc.pki.ca.config.SNHandlerProperties;
import java.math.BigInteger;

public interface SNHandler {

   // This method is supposed to get and lock a serial number, the question is:
   // would I should release the file for another request to pick a serial
   // number how to handle two or more requests to prevent losing a sequence?
   public void init(SNHandlerProperties props);
   public BigInteger lockNextSn();
   public BigInteger getNextSn();
   public BigInteger popLastSn();
   public void commit() throws Exception;
}
