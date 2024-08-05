package com.lahiru.demo;

import com.lahiru.demo.model.ToolRentalCharge;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.HolidayCalendar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@ActiveProfiles("test")
public class DaysCalculatorTest {

    @Autowired
    DaysCalculator daysCalculator;

    @Configuration
    public static class Config {
        @Bean
        public DaysCalculator daysCalculator(){
            return new DaysCalculator(new HolidayCalendar());
        }
    }

    @Test
    public void test_charge_days(){
        LocalDate checkoutDate = LocalDate.of(2015,9,3);
        LocalDate dueDate = daysCalculator.getDueDate(checkoutDate,6);
        long chargeDays = daysCalculator.getChargeDays(ToolRentalCharge.JACKHAMMER_RENTAL,checkoutDate,dueDate);
        Assertions.assertEquals(3,chargeDays);
    }

}
