package com.lahiru.demo.model;

import lombok.Getter;

@Getter
public enum Tool {

    CHNS("CHNS", ToolType.CHAINSAW, Brand.STIHL),
    LADW("LADW",ToolType.LADDER,Brand.WERNER),
    JAKD("JAKD",ToolType.JACKHAMMER,Brand.DEWALT),
    JAKR("JAKR",ToolType.JACKHAMMER,Brand.RIDGID);

    private final String toolCode;
    private final ToolType toolType;
    private final Brand brand;

    Tool(String toolCode, ToolType toolType, Brand brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "{" +
                "toolCode='" + toolCode + '\'' +
                ", toolType=" + toolType.getName() +
                ", brand=" + brand.getName() +
                '}';
    }
}
