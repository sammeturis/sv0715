package com.acme.rental.model.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RentalCheckOutRequest {
    
    @NotBlank(message = "Tool code is required")
    private String toolCode;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd", fallbackPatterns = {"MM/dd/yyyy"})
    private LocalDate checkoutDate;
    
    @Min(value = 1, message = "Rental days must be at least 1")
    private int rentalDays;

    @Min(value = 0, message = "Discount percent must be between 0 and 100")
    @Max(value = 100, message = "Discount percent must be between 0 and 100")
    private int discountPercent;
}
