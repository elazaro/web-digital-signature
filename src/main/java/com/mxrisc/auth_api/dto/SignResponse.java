package com.mxrisc.auth_api.dto;

public class SignResponse {
   private String signature;

   public SignResponse(String signature) {
      this.signature = signature;
   }

   public String getSignature() { return signature; }
}
