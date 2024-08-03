package com.lahiru.demo.util;

import com.lahiru.demo.model.ToolRentalCharge;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateCalculator {

    HolidayCalendar holidayCalendar;

    public DateCalculator(HolidayCalendar holidayCalendar) {
        this.holidayCalendar = holidayCalendar;
    }

    public long getChargeDays(ToolRentalCharge toolRentalCharge, LocalDate checkoutDate, LocalDate dueDate) {
        long chargeDays = 0;
        final DayOfWeek startDoW = checkoutDate.getDayOfWeek();
        final DayOfWeek endDoW = dueDate.getDayOfWeek();
        final long days = ChronoUnit.DAYS.between(checkoutDate, dueDate);
        final long daysWithoutWeekends = days - 2 * ((days + startDoW.getValue()) / 7);
        

        int holidays = getHolidays(checkoutDate, dueDate);
        //check the tool type and calculate charge days
        return 0;
    }

    public LocalDate getDueDate(LocalDate checkoutDate, long rentalDays) {
        return checkoutDate.plusDays(rentalDays);
    }

    private int getHolidays(LocalDate checkoutDate, LocalDate dueDate) {
        return 0;
    }
}
