package com.acme.rental.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.rental.model.request.RentalCheckOutRequest;
import com.acme.rental.model.response.RentalCheckOutResponse;
import com.acme.rental.service.RentalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rental")
public class RateInfoController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/checkout")
    public ResponseEntity<RentalCheckOutResponse> calculateRentalCharge(@Valid @RequestBody RentalCheckOutRequest request) {
           
        try {
            RentalCheckOutResponse rateResp = rentalService.calculateRentalCharge(
                request.getToolCode(), 
                request.getRentalDays(), 
                request.getDiscountPercent(), 
                request.getCheckoutDate()
            );

            return ResponseEntity.ok(rateResp);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Additional endpoints for managing rate information can be added here
}
