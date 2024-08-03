package com.lahiru.demo.model;

import lombok.Getter;

@Getter
public enum ToolType {

    CHAINSAW("Chainsaw"),
    LADDER("Ladder"),
    JACKHAMMER("Jackhammer")
    ;

    private final String name;

    ToolType(String name) {
        this.name = name;
    }
}
