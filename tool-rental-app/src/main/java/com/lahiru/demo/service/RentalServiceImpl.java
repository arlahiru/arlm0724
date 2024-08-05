package com.lahiru.demo.service;

import com.lahiru.demo.exception.RentalAgreementException;
import com.lahiru.demo.model.RentalAgreement;
import com.lahiru.demo.model.Tool;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.RentalCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final DaysCalculator daysCalculator;
    private final RentalCalculator rentalCalculator;

    @Autowired
    public RentalServiceImpl(DaysCalculator daysCalculator, RentalCalculator rentalCalculator) {
        this.daysCalculator = daysCalculator;
        this.rentalCalculator = rentalCalculator;
    }

    @Override
    public RentalAgreement checkout(String toolCode, int rentalDays, LocalDate checkoutDate, int discountPercentage) throws RentalAgreementException {

        Tool rentingTool = Tool.getByToolCode(toolCode);
        RentalAgreement.RentalAgreementBuilder rentalAgreementBuilder = new RentalAgreement.RentalAgreementBuilder(daysCalculator,rentalCalculator);
        return rentalAgreementBuilder
                .tool(rentingTool)
                .rentalDays(rentalDays)
                .discountPercentage(discountPercentage)
                .checkoutDays(checkoutDate)
                .build();
    }
}
