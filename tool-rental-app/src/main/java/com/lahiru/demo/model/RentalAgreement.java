package com.lahiru.demo.model;

import com.lahiru.demo.exception.RentalAgreementException;
import com.lahiru.demo.util.DaysCalculator;
import com.lahiru.demo.util.Formatter;
import com.lahiru.demo.util.RentalCalculator;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RentalAgreement {

    private DaysCalculator daysCalculator;
    private RentalCalculator rentalCalculator;
    private Tool tool;
    private long rentalDays;
    private LocalDate checkoutDate;
    private int discountPercent;
    private LocalDate dueDate;
    private ToolRentalCharge toolRentalCharge;
    private long chargeDays;
    private double preDiscountCharge;
    private double discountAmount;
    private double finalCharge;

    private RentalAgreement(RentalAgreementBuilder builder) {
        this.daysCalculator = builder.daysCalculator;
        this.rentalCalculator = builder.rentalCalculator;
        this.tool = builder.tool;
        this.rentalDays = builder.rentalDays;
        this.checkoutDate = builder.checkoutDate;
        this.discountPercent = builder.discountPercent;
        this.toolRentalCharge = ToolRentalCharge.get(tool.getToolType());
        //calculate due date and charge days
        this.dueDate = daysCalculator.getDueDate(checkoutDate,rentalDays);
        this.chargeDays = daysCalculator.getChargeDays(toolRentalCharge,checkoutDate,dueDate);
        //calculate discount and final rent amount
        this.preDiscountCharge = rentalCalculator.getPreDiscountCharge(chargeDays,toolRentalCharge.getDailyCharge());
        this.discountAmount = rentalCalculator.getDiscountAmount(preDiscountCharge,discountPercent);
        this.finalCharge = rentalCalculator.getFinalCharge(preDiscountCharge,discountAmount);
    }

    public void print(){
        StringBuilder rentalAgreement = new StringBuilder();
        rentalAgreement.append("\n").append("********** Rental Agreement **********").append("\n").append("\n")
                .append("Tool Code: ").append(tool.getToolCode()).append("\n")
                .append("Tool Type: ").append(tool.getToolType().getName()).append("\n")
                .append("Tool Brand: ").append(tool.getBrand().getName()).append("\n")
                .append("Rental Days: ").append(rentalDays).append("\n")
                .append("Checkout Date: ").append(Formatter.date(checkoutDate)).append("\n")
                .append("Due Date: ").append(Formatter.date(dueDate)).append("\n")
                .append("Daily Rental Charge: ").append(Formatter.currency(toolRentalCharge.getDailyCharge())).append("\n")
                .append("Charge Days: ").append(chargeDays).append("\n")
                .append("Pre-discount Charge: ").append(Formatter.currency(preDiscountCharge)).append("\n")
                .append("Discount Percent: ").append(Formatter.percentage(discountPercent)).append("\n")
                .append("Discount Amount: ").append(Formatter.currency(discountAmount)).append("\n")
                .append("Final Charge: ").append(Formatter.currency(finalCharge)).append("\n").append("\n")
                .append("********** Thank You **********").append("\n");
        System.out.println(rentalAgreement.toString());
    }

    //Builder class for RentalAgreement
    public static class RentalAgreementBuilder {
        private final DaysCalculator daysCalculator;
        private final RentalCalculator rentalCalculator;
        private Tool tool;
        private long rentalDays;
        private LocalDate checkoutDate;
        private int discountPercent;

        public RentalAgreementBuilder(DaysCalculator daysCalculator, RentalCalculator rentalCalculator) {
            this.daysCalculator = daysCalculator;
            this.rentalCalculator = rentalCalculator;
        }

        public RentalAgreementBuilder tool(Tool tool){
            this.tool = tool;
            return this;
        }

        public RentalAgreementBuilder rentalDays(long rentalDays){
            this.rentalDays = rentalDays;
            return this;
        }

        public RentalAgreementBuilder checkoutDays(LocalDate checkoutDate){
            this.checkoutDate = checkoutDate;
            return this;
        }

        public RentalAgreementBuilder discountPercentage(int discountPercent){
            this.discountPercent = discountPercent;
            return this;
        }

        public RentalAgreement build() throws RentalAgreementException{
            validate();
            return new RentalAgreement(this);
        }

        private void validate() throws RentalAgreementException {
            StringBuilder validationErrors = new StringBuilder();
            if(rentalDays < 1){
                validationErrors.append("Rental days must be greater than 0").append("\n");
            }
            if(discountPercent < 0 || discountPercent > 100){
                validationErrors.append("Discount percent must be between 0 to 100").append("\n");
            }
            if(tool == null){
                validationErrors.append("Tool cannot be empty").append("\n");
            }
            if(checkoutDate == null){
                validationErrors.append("Checkout date cannot be empty").append("\n");
            }
            if(!validationErrors.isEmpty()){
                throw new RentalAgreementException(validationErrors.toString());
            }
        }
    }

}
