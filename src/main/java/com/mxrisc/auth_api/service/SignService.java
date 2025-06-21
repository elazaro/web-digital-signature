package com.mxrisc.auth_api.service; 

import com.mxrisc.security.DigitalSigner;
import com.mxrisc.auth_api.config.SignerProperties;
import org.springframework.stereotype.Service;

@Service
public class SignService {
   private final DigitalSigner signer;

   public SignService(SignerProperties props) throws Exception {
      // TODO: Change path "java.p12" to use @ConfigurationProperties, and maybe using hashicorp.
      // Note: Fri Jun 20 14:47:59 CST 2025: Changed for @ConfigurationProperties as spring uses.
      this.signer = new DigitalSigner(props.getPath(), props.getPassword(), props.getAlias());
   }

   public String sign(String payload) throws Exception {
      return signer.signToBase64(payload);
   }

}
