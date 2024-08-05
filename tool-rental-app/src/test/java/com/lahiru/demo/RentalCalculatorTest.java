package com.lahiru.demo;

import com.lahiru.demo.service.RentalService;
import com.lahiru.demo.service.RentalServiceImpl;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.HolidayCalendar;
import com.lahiru.demo.util.RentalCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RentalCalculatorTest {

    @Autowired
    RentalCalculator rentalCalculator;

    @Configuration
    public static class Config {
        @Bean
        public RentalCalculator rentalCalculator(){
            return new RentalCalculator();
        }
    }

    @Test
    public void test_1_pre_discount_charge(){
        double value = rentalCalculator.getPreDiscountCharge(10,2.5);
        Assertions.assertEquals((10 * 2.5), value);
    }

    @Test
    public void test_2_discount_amount(){
        double value = rentalCalculator.getDiscountAmount(25,10);
        Assertions.assertEquals((25.0 * ((double) 10 /100)), value);
    }

    @Test
    public void test_2_final_charge(){
        double value = rentalCalculator.getFinalCharge(25.0, 2.5);
        Assertions.assertEquals((25.0 - 2.5), value);
    }
}
