package com.example.Calendar.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    GUEST(0),
    USER(1),
    ADMIN(2);

    private final int value;

    Role(int value) {
        this.value = value;
    }

}
