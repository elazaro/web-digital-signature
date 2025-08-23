package com.mxrisc.auth_api.controller; 

import com.mxrisc.auth_api.dto.SignRequest;
import com.mxrisc.auth_api.dto.SignResponse;
import com.mxrisc.auth_api.service.SignService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/sign")
public class SignController {

   private final SignService signService;

   public SignController(SignService signService) {
      this.signService = signService;
   }

   @PostMapping
   @PreAuthorize("hasRole('ROLE_SIGNER')")
   public SignResponse sign(@RequestBody SignRequest request) throws Exception {
      String signature = signService.sign(request.getPayload());
      return new SignResponse(signature);
   } 
}
