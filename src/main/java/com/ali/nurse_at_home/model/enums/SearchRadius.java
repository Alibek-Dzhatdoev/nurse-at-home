package com.ali.nurse_at_home.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SearchRadius {

    ONE(1),
    THREE(3),
    FIVE(5),
    TEN(10),;

    public final int number;
}
