package com.lahiru.demo.util;

import org.springframework.stereotype.Component;

@Component
public class RentalCalculator {

    public double getPreDiscountCharge(long chargeDays, double dailyCharge){
        return chargeDays * dailyCharge;
    }

    public double getDiscountAmount(double preDiscountCharge, int percentage){
        return preDiscountCharge * ((double) percentage /100);
    }

    public double getFinalCharge(double preDiscountCharge, double discountAmount){
        return preDiscountCharge - discountAmount;
    }



}
