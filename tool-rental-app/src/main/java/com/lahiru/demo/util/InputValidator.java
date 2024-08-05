package com.lahiru.demo.util;

import com.lahiru.demo.exception.InvalidInputException;
import com.lahiru.demo.model.RentalAgreement;
import com.lahiru.demo.model.Tool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputValidator {

    public static void validateInputs(String toolCode,  String rentalDays, String checkoutDate, String discountPercentage) throws InvalidInputException {
        StringBuilder errors = new StringBuilder();
        if (Tool.getByToolCode(toolCode) == null ){
            errors.append("* Please enter valid tool code as shown in hints.").append("\n");
        }
        try{
            Integer.parseInt(rentalDays);
        }catch (NumberFormatException e){
            errors.append("* Please enter numeric value for rental days.").append("\n");
        }
        try {
            LocalDate.parse(checkoutDate, DateTimeFormatter.ofPattern(Formatter.DATE_FORMAT));
        }catch(DateTimeParseException e){
            errors.append("* Please enter valid checkout date in mm/dd/yy format. E.g. 07/15/23.").append("\n");
        }
        try {
            Integer.parseInt(discountPercentage);
        }catch (NumberFormatException e){
            errors.append("* Please enter numeric value for discount.").append("\n");
        }
        if(!errors.isEmpty()){
            throw new InvalidInputException(errors.toString());
        }
    }
}
