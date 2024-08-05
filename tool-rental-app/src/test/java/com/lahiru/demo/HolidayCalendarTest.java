package com.lahiru.demo;

import com.lahiru.demo.util.HolidayCalendar;
import com.lahiru.demo.util.RentalCalculator;
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
public class HolidayCalendarTest {

    @Autowired
    HolidayCalendar holidayCalendar;

    @Configuration
    public static class Config {
        @Bean
        public HolidayCalendar holidayCalculator(){
            return new HolidayCalendar();
        }
    }

    @Test
    public void test_Independence_Day_Weekday(){
        LocalDate independenceDayOf2024 = holidayCalendar.dayOf(HolidayCalendar.Holiday.INDEPENDENCE_DAY,2024); // Weekday
        Assertions.assertEquals("07/04/24",independenceDayOf2024.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    }

    @Test
    public void test_Independence_Day_Sunday(){
        LocalDate independenceDayOf2021 = holidayCalendar.dayOf(HolidayCalendar.Holiday.INDEPENDENCE_DAY,2021); //Sunday
        Assertions.assertEquals("07/05/21",independenceDayOf2021.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    }

    @Test
    public void test_Independence_Day_Saturday(){
        LocalDate independenceDayOf2020 = holidayCalendar.dayOf(HolidayCalendar.Holiday.INDEPENDENCE_DAY,2020); ; // Saturday
        Assertions.assertEquals("07/03/20",independenceDayOf2020.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    }

    @Test
    public void test_Labor_Day_2024(){
        LocalDate laborDayOf2024 = holidayCalendar.dayOf(HolidayCalendar.Holiday.LABOR_DAY,2024);
        Assertions.assertEquals("09/02/24",laborDayOf2024.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    }

    @Test
    public void test_Labor_Day_2023(){
        LocalDate laborDayOf2023 = holidayCalendar.dayOf(HolidayCalendar.Holiday.LABOR_DAY,2023);
        Assertions.assertEquals("09/04/23",laborDayOf2023.format(DateTimeFormatter.ofPattern("MM/dd/yy")));
    }

    @Test
    public void test_Labor_Day_2020(){
        LocalDate laborDayOf2020 = holidayCalendar.dayOf(HolidayCalendar.Holiday.LABOR_DAY,2020);
        Assertions.assertEquals("09/07/20",laborDayOf2020.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

    }
}
