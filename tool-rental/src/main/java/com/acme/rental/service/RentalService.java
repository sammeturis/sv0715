package com.acme.rental.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.rental.entity.Holiday;
import com.acme.rental.entity.RateInfo;
import com.acme.rental.entity.Tool;
import com.acme.rental.model.response.RentalCheckOutResponse;
import com.acme.rental.repository.HolidayRepository;
import com.acme.rental.repository.RateInfoRepository;
import com.acme.rental.repository.ToolRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalService {
    
    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private RateInfoRepository rateInfoRepository;

    @Autowired
    private HolidayRepository holidayRepository;


    public RentalCheckOutResponse calculateRentalCharge(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        
        
        Tool tool = getToolWithRateInfo(toolCode);
        RateInfo rateInfo = rateInfoRepository.findByToolType(tool.getToolType());
        List<Holiday> holidays = holidayRepository.findAll();

        RentalCheckOutResponse response = new RentalCheckOutResponse();
        
        int effectiveRentalDays = calculateChargeableDays(rentalDays, checkoutDate, rateInfo, holidays);

        BigDecimal charge = rateInfo.getDailyCharge().multiply(BigDecimal.valueOf(effectiveRentalDays));
        BigDecimal totalCharge = charge.subtract(charge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100)));

        response.setFinalCharge(totalCharge.doubleValue());
        response.setToolCode(tool.getToolCode());
        response.setToolType(tool.getToolType());
        response.setToolBrand(tool.getBrand());
        response.setRentalDays(rentalDays);
        response.setCheckoutDate(checkoutDate);
        response.setDailyRentalCharge(rateInfo.getDailyCharge().doubleValue());
        response.setChargeDays(effectiveRentalDays);
        response.setPreDiscountCharge(charge.doubleValue());
        response.setDiscountPercent(discountPercent);
        response.setDiscountAmount(charge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100)).doubleValue());
        response.setDueDate(checkoutDate.plusDays(rentalDays));
        
        return response; 
    }

    /**
     * Get the tool with the rate information based on the tool code
     * 
     * @param toolCode
     * @return
     */
    private Tool getToolWithRateInfo(String toolCode) {
        return toolRepository.findByToolCode(toolCode)
                .orElseThrow(() -> new EntityNotFoundException("Tool not found:: " + toolCode));
    }

    /**
     * determine the number of chargeable days based on the rental days and the holidays
     * 
     * @param rentalDays
     * @param checkoutDate
     * @param rateInfo
     * @param holidays
     * @return
     */
    private int calculateChargeableDays(int rentalDays, LocalDate checkoutDate, RateInfo rateInfo, List<Holiday> holidays) {
        int nonChargeableDays = 0;
        LocalDate currentDate = checkoutDate;
        for (int i = 0; i < rentalDays; i++) {
            if (!isChargebleDay(currentDate, rateInfo, holidays)) {
                nonChargeableDays++;
            }
            // handle if the day is weekend and holiday to add observed holiday
            // to increase the chargeable days
            if(isWeekend(currentDate) && isHoliday(currentDate, holidays).isPresent() && !rateInfo.isHolidayCharge()) {
                nonChargeableDays++;
            }

            currentDate = currentDate.plusDays(1);
        }

        return (rentalDays - nonChargeableDays);
    }

    /**
     * Determine if the day is chargeable based on the rate info and the holidays
     * 
     * @param date
     * @param rateInfo
     * @param holidays
     * @return
     */
    private boolean isChargebleDay(LocalDate date, RateInfo rateInfo, List<Holiday> holidays) {
        Boolean isChargebleDay = false;
        Optional<Holiday> holiday = isHoliday(date, holidays);
        
        if ( (!isWeekend(date) && rateInfo.isWeekdayCharge()) || //if weeday and weekday charge is allowed
                (isWeekend(date) && rateInfo.isWeekendCharge()) || //if weekend and weekend charge is allowed
                (holiday.isPresent() && rateInfo.isHolidayCharge()) ){ //if holiday and holiday charge is allowed
            isChargebleDay = true;
        }
        
        return isChargebleDay;
    }

    /**
     * Check if the date is a holiday
     * 
     * @param date
     * @param holidays
     * @return
     */
    @SuppressWarnings("unlikely-arg-type")
    private Optional<Holiday> isHoliday(LocalDate date, List<Holiday> holidays) {
       return holidays.stream().filter(holiday -> holiday.getDate().equals(date)).findFirst();
    }

    /**
     * Check if the date is a weekend
     * @param date
     * @return
     */
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
