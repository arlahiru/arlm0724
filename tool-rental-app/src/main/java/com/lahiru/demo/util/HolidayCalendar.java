package com.lahiru.demo.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HolidayCalendar {

    @Getter
    public static enum Holiday {

        INDEPENDENCE_DAY("Independence Day"),
        LABOR_DAY("Labor day");
        //CHRISTMAS_DAY(12,25, "Christmas Day");

        private int month;
        private int day;
        private final String description;

        Holiday(String description) {
            this.description = description;
        }

        Holiday(int month, int day, String description) {
            this.month = month;
            this.day = day;
            this.description = description;
        }
    }

    Map<Integer, List<LocalDate>> holidaysCacheByYear = new HashMap<>();

    public LocalDate dayOf(Holiday holiday, int year) {
        LocalDate result = null;
        if(holiday.equals(Holiday.INDEPENDENCE_DAY)){
            LocalDate july4th = LocalDate.of(year,7,4);
            int day = july4th.getDayOfWeek().getValue();
            if(day == 6) // if Saturday return July 3rd Friday
                result = LocalDate.of(year,7,3);
            else if (day == 7) // if Sunday return July 5th Monday
                result = LocalDate.of(year,7,5);
            else
                result = july4th;
        }else if(holiday.equals(Holiday.LABOR_DAY)){
            LocalDate currentDate = LocalDate.of(year,9,1); // Sept 1
            LocalDate sept8th = LocalDate.of(year,9,8); //Sept 8th
            do{
                int dayOfWeek = currentDate.getDayOfWeek().getValue();
                if(dayOfWeek == 1) {// Monday
                    result = currentDate;
                    break;
                }
                currentDate = currentDate.plusDays(1);
            } while (!currentDate.equals(sept8th));
            result = currentDate;
        }else {
            result = LocalDate.of(year,holiday.getMonth(),holiday.getDay());
        }
        return result;
    }

    private List<LocalDate> getHolidaysByYear(int year){
        //check the cache first
        if(holidaysCacheByYear.get(year) != null)
            return holidaysCacheByYear.get(year);
        else{
            List<LocalDate> holidays = new ArrayList<>();
            //if cache miss build the holidays cache for given year
            for(Holiday holiday: Holiday.values()){
                 LocalDate date = dayOf(holiday,year);
                 holidays.add(date);
            }
            holidaysCacheByYear.put(year, holidays);
            return holidays;
        }
    }

    public boolean isHoliday(LocalDate date){
        int year = date.getYear();
        List<LocalDate> holidayList = getHolidaysByYear(year);
        return holidayList.contains(date);
    }

}
