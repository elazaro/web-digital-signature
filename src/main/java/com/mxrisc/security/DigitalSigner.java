package com.mxrisc.security;

import com.mxrisc.pki.ca.api.SNHandler;
import com.mxrisc.pki.ca.impl.JSonSNHandler;
import com.mxrisc.pki.ca.SNFactory;

import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.encoders.Base64;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DigitalSigner {
   private static final Logger log = LoggerFactory.getLogger("com.mxrisc");

   private final PrivateKey privateKey;
   private final X509Certificate certificate;

   static {
      Security.addProvider(new BouncyCastleProvider());
   }

   public DigitalSigner(String keystorePath, String keystorePassword, String alias) throws Exception {
      KeyStore keystore = KeyStore.getInstance("PKCS12");

      // Note: fis implements AutoCloseable interface, this means, the try with
      // resource statement will automatically close the resource fis once the
      // load method is completed. this feature was introduced in Java8, how old
      // I am. LOL.
      try(FileInputStream fis = new FileInputStream(keystorePath)) {
         keystore.load(fis, keystorePassword.toCharArray());
      }

      this.privateKey = (PrivateKey)keystore.getKey(alias, keystorePassword.toCharArray());
      this.certificate = (X509Certificate) keystore.getCertificate(alias);
   }

   public byte[] signDetached(byte[] data) throws Exception {
      log.info("Enter signDetached");
      CMSTypedData msg = new CMSProcessableByteArray(data);
      
      CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
      ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA")
         .setProvider("BC")
         .build(privateKey);

      gen.addSignerInfoGenerator(
            new JcaSignerInfoGeneratorBuilder(
               new JcaDigestCalculatorProviderBuilder().setProvider("BC").build())
            .build(signer, certificate));

      gen.addCertificates(new JcaCertStore(Collections.singletonList(certificate)));

      // TODO: false means "detached", what's that?
      CMSSignedData signedData = gen.generate(msg, false);

      log.info("Exit signDetached");
      return signedData.getEncoded();
   }

   public String signToBase64(String data) throws Exception {
      log.info("Enter signToBase64");
      // TODO: This uses UTF-8, what is something like ISO-8859-1 should we
      // detect this from the environment or marshall unmarshall before signing
      // to warraty compatibility
      byte[] signed = signDetached(data.getBytes(StandardCharsets.UTF_8));
      log.info("Exit signToBase64");
      return Base64.toBase64String(signed);
   }

}
