package com.lahiru.demo.util;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class HolidayCalendar {

    @Getter
    public static enum Holiday {

        INDEPENDENCE_DAY("Independence Day"),
        LABOR_DAY("Labor day");

        private final String description;

        Holiday(String description) {
            this.description = description;
        }
    }

    private Map<String, LocalDate> holidaysCache = new HashMap<>();

    public LocalDate dayOf(Holiday holiday, int year) {
        //check the cache first
        LocalDate result = holidaysCache.get(year+"_"+holiday.name());;
        if(result != null) {
            return result;
        }

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
            LocalDate sept8th = LocalDate.of(year,9,7); //Sept 8th
            do{
                int dayOfWeek = currentDate.getDayOfWeek().getValue();
                if(dayOfWeek == 1) {// Monday
                    result = currentDate;
                    break;
                }
                currentDate = currentDate.plusDays(1);
            } while (!currentDate.equals(sept8th));
            result = currentDate;
        }
        //put holiday date to cache
        holidaysCache.put(year+"_"+holiday.name(),result);
        return result;
    }

    public boolean isHoliday(LocalDate date){
        return false;
    }

}
