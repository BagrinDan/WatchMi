package com.example.Calendar.model.enums;

import lombok.Getter;

@Getter
public enum Gender {
    OTHER(0),
    MALE(1),
    FEMALE(2);

    private final int value;

    Gender(int value) {
        this.value = value;
    }

}
