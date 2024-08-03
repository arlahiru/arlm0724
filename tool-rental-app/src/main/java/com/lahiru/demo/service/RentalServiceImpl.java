package com.lahiru.demo.service;

import com.lahiru.demo.model.RentalAgreement;
import com.lahiru.demo.util.RentalCalculator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RentalServiceImpl implements RentalService {

    @Override
    public RentalAgreement checkout(String toolCode, int rentalDayCount, int discountPercentage, LocalDate checkoutDate) {
        return null;
    }
}
