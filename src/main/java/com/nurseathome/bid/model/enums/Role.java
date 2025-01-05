package com.nurseathome.bid.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {

    PATIENT("patient"),
    SERVICE("service"),
    SUPER_ADMIN("super_admin"),
    NURSE("nurse");

    public final String description;
}
