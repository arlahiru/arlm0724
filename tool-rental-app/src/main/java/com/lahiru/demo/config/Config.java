package com.lahiru.demo.config;

import com.lahiru.demo.service.RentalService;
import com.lahiru.demo.service.RentalServiceImpl;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.HolidayCalendar;
import com.lahiru.demo.util.RentalCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RentalService rentalService(){
        return new RentalServiceImpl(new DaysCalculator(new HolidayCalendar()), new RentalCalculator());
    }
}
