package com.nurseathome.bid.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SearchRadius {

    ONE(1),
    THREE(3),
    FIVE(5),
    TEN(10),;

    public final int number;
}
