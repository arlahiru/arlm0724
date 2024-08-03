package com.lahiru.demo.model;

import com.lahiru.demo.util.DateCalculator;
import com.lahiru.demo.util.RentalCalculator;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RentalAgreement {

    public final String DATE_FORMAT = "";
    public final String CURRENCY_FORMAT = "";
    public final String PERCENTAGE_FORMAT = "";

    private DateCalculator dateCalculator;
    private RentalCalculator rentalCalculator;
    private Tool tool; //Specified at checkout
    private long rentalDays; //Specified at checkout
    private LocalDate checkoutDate; //Specified at checkout
    private LocalDate dueDate; //Calculated from checkout date and rental days.
    private ToolRentalCharge toolRentalCharge;  //Amount per day, specified by the tool type.
    private long chargeDays; //Count of chargeable days, from day after checkout through and including due date, excluding “no charge” days as specified by the tool type.
    private double preDiscountCharge; //Calculated as charge days X daily charge. Resulting total rounded half up to cents.
    private int discountPercent; //Specified at checkout.
    double discountAmount; //calculated from discount % and pre-discount charge. Resulting amount rounded half up to cents.
    private double finalCharge; //Calculated as pre-discount charge - discount amount.

    //TODO apply builder pattern here
    public RentalAgreement(DateCalculator dateCalculator, RentalCalculator rentalCalculator, Tool tool, long rentalDays, LocalDate checkoutDate, int discountPercent) {
        this.dateCalculator = dateCalculator;
        this.rentalCalculator = rentalCalculator;
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.discountPercent = discountPercent;
        setToolRentalCharge(ToolRentalCharge.get(tool.getToolType()));
        setDueDate(dateCalculator.getDueDate(checkoutDate,rentalDays));
        setChargeDays(dateCalculator.getChargeDays(toolRentalCharge,checkoutDate,dueDate));
    }

    public void generateAgreement(){
        setPreDiscountCharge(rentalCalculator.getPreDiscountCharge(chargeDays,toolRentalCharge.getDailyCharge()));
        setDiscountAmount(rentalCalculator.getDiscountAmount(preDiscountCharge, discountPercent));
        setFinalCharge(rentalCalculator.getFinalCharge(preDiscountCharge,discountAmount));
        //print rental agreement to console
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "tool=" + tool +
                ", rentalDays=" + rentalDays +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", toolRentalCharge=" + toolRentalCharge +
                ", chargeDays=" + chargeDays +
                ", preDiscountCharge=" + preDiscountCharge +
                ", discountPercent=" + discountPercent +
                ", discountAmount=" + discountAmount +
                ", finalCharge=" + finalCharge +
                '}';
    }

}
