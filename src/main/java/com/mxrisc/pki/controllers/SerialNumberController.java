package com.mxrisc.pki.controllers;

import com.mxrisc.pki.services.dto.SerialNumberResponse;
import com.mxrisc.pki.services.SerialNumbersService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.access.prepost.PreAuthorize;

// Note: Fri Aug 22 17:04:16 CST 2025: What if I want a single controller for
// different methods?
@RestController
@RequestMapping("/serialnumber")
public class SerialNumberController {

   public final SerialNumbersService snService;

   public SerialNumberController(SerialNumbersService snService) {
      this.snService = snService;
   }

   // TODO: Fri Aug 22 17:04:51 CST 2025: Change the role for something
   // meaningfull for an CA application which will consume the service.
   @GetMapping
   @PreAuthorize("hasRole('ROLE_SIGNER')")
   public SerialNumberResponse serialNumber() {
      String nextNumber = snService.getNextNumber();
      return new SerialNumberResponse(nextNumber);
   }

}
