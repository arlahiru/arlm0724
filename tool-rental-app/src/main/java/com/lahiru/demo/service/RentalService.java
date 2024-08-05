package com.lahiru.demo.service;

import com.lahiru.demo.exception.RentalAgreementException;
import com.lahiru.demo.model.RentalAgreement;

import java.time.LocalDate;


public interface RentalService {
    public RentalAgreement checkout(String toolCode, int rentalDays, LocalDate checkoutDate, int discountPercentage) throws RentalAgreementException;
}
