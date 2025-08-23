package com.mxrisc.pki.services.dto;

public class SerialNumberResponse {
   public String serialNumber;

   public SerialNumberResponse(String serialNumber) {
      this.serialNumber = serialNumber;
   }

   public String getSerialNumber() { return this.serialNumber; }
}
