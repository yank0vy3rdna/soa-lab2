package ru.yank0vy3rdna.soa.lab3.common.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets Status
 */
public enum Status {
    FIRED("FIRED"),
    HIRED("HIRED"),
    RECOMMENDED_FOR_PROMOTION("RECOMMENDED_FOR_PROMOTION");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static Status fromValue(String text) {
        for (Status b : Status.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
