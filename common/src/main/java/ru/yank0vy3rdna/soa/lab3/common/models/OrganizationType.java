package ru.yank0vy3rdna.soa.lab3.common.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * Gets or Sets OrganizationType
 */
public enum OrganizationType implements Serializable {
    COMMERCIAL("COMMERCIAL"),
    PUBLIC("PUBLIC"),
    PRIVATE_LIMITED_COMPANY("PRIVATE_LIMITED_COMPANY"),
    OPEN_JOINT_STOCK_COMPANY("OPEN_JOINT_STOCK_COMPANY");

    private final String value;
    private static final long serialVersionUID = -558553967080513790L;

    OrganizationType(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static OrganizationType fromValue(String text) {
        for (OrganizationType b : OrganizationType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
