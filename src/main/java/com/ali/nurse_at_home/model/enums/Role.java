package com.ali.nurse_at_home.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {

    PATIENT("patient"),
    SUPER_ADMIN("super_admin"),
    NURSE("nurse");

    public final String description;
}