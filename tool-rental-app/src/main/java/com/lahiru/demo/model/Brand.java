package com.lahiru.demo.model;

import lombok.Getter;

@Getter
public enum Brand {
    STIHL("Stihl"),
    WERNER("Werner"),
    DEWALT("DeWalt"),
    RIDGID("Ridgid");

    private final String name;

    Brand(String name) {
        this.name = name;
    }
}
