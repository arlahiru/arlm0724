package com.lahiru.demo.model;

import lombok.Getter;

@Getter
public enum ToolRentalCharge {

    LADDER_RENTAL(ToolType.LADDER, 1.99, true, true, false),
    CHAINSAW_RENTAL(ToolType.CHAINSAW, 1.49, true, false, true),
    JACKHAMMER_RENTAL(ToolType.JACKHAMMER, 2.99, true, false, false)
    ;

    private final ToolType toolType;
    private final double dailyCharge;
    private final boolean isWeekdayCharge;
    private final boolean isWeekendCharge;
    private final boolean isHolidayCharge;

    ToolRentalCharge(ToolType toolType, double dailyCharge, boolean isWeekdayCharge, boolean isWeekendCharge, boolean isHolidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.isWeekdayCharge = isWeekdayCharge;
        this.isWeekendCharge = isWeekendCharge;
        this.isHolidayCharge = isHolidayCharge;
    }

    public static ToolRentalCharge get(ToolType type){
        return switch(type) {
            case LADDER -> LADDER_RENTAL;
            case CHAINSAW -> CHAINSAW_RENTAL;
            case JACKHAMMER -> JACKHAMMER_RENTAL;
        };
    }
}
