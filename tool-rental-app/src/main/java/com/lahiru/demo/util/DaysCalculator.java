package com.lahiru.demo.util;

import com.lahiru.demo.model.ToolRentalCharge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.Predicate;

@Component
@Slf4j
public class DaysCalculator {

    HolidayCalendar holidayCalendar;
    Predicate<LocalDate> isWeekend = new Predicate<LocalDate>() {
        @Override
        public boolean test(LocalDate localDate) {
            return localDate.getDayOfWeek().getValue() > 5;
        }
    };

    Predicate<LocalDate> isWeekday = new Predicate<LocalDate>() {
        @Override
        public boolean test(LocalDate localDate) {
            return localDate.getDayOfWeek().getValue() <= 5;
        }
    };

    public DaysCalculator(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    public long getChargeDays(ToolRentalCharge toolRentalCharge, LocalDate checkoutDate, LocalDate dueDate) {
        long chargeDays = 0;
        LocalDate endDate = dueDate.plusDays(1);
        //calculate weekdays count
        long weekdays = checkoutDate.datesUntil(endDate)
                .filter(isWeekday)
                .filter(date -> !holidayCalendar.isHoliday(date))
                .count();
        log.debug("Total Weekdays:"+ weekdays);
        //calculate weekends count
        long weekends = checkoutDate.datesUntil(endDate)
                .filter(isWeekend)
                .count();
        log.debug("Total Weekends:"+ weekends);
        //calculate holidays count
        long holidays = checkoutDate.datesUntil(endDate)
                .filter(date -> holidayCalendar.isHoliday(date))
                .count();
        log.debug("Total Holidays:"+ holidays);
        //check the tool type configuration and calculate charge days
        if(toolRentalCharge.isWeekdayCharge()) {
            chargeDays = chargeDays + weekdays;
        }
        if(toolRentalCharge.isWeekendCharge()) {
            chargeDays = chargeDays + weekends;
        }
        if(toolRentalCharge.isHolidayCharge()) {
            chargeDays = chargeDays + holidays;
        }
        log.debug("Total Charge Days:"+ chargeDays);
        return chargeDays;
    }

    public LocalDate getDueDate(LocalDate checkoutDate, long rentalDays) {
        return checkoutDate.plusDays(rentalDays-1);
    }

}
