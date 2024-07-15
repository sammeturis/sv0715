package com.acme.rental.model.response;

import java.time.LocalDate;

import com.acme.rental.config.CurrencySerializer;
import com.acme.rental.config.PercentageSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RentalCheckOutResponse {
    private String toolCode;
    private String toolType;
    private String toolBrand;
    private int rentalDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yy")
    private LocalDate checkoutDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yy")
    private LocalDate dueDate;
    @JsonSerialize(using = CurrencySerializer.class)
    private double dailyRentalCharge;
    private int chargeDays;
    @JsonSerialize(using = CurrencySerializer.class)
    private double preDiscountCharge;
    @JsonSerialize(using = PercentageSerializer.class)
    private int discountPercent;
    @JsonSerialize(using = CurrencySerializer.class)
    private double discountAmount;
    @JsonSerialize(using = CurrencySerializer.class)
    private double finalCharge;
}
