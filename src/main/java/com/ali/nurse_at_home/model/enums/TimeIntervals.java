package com.ali.nurse_at_home.model.enums;

import lombok.AllArgsConstructor;

import java.time.LocalTime;

import static java.time.LocalTime.of;

@AllArgsConstructor
public enum TimeIntervals {

    FROM_00_00_TO_00_30(of(0,0)),
    FROM_00_30_TO_01_00(of(0,30)),
    FROM_01_00_TO_01_30(of(1,0)),
    FROM_01_30_TO_02_00(of(1,30)),
    FROM_02_00_TO_02_30(of(2,0)),
    FROM_02_30_TO_03_00(of(2,30)),
    FROM_03_00_TO_03_30(of(3,0)),
    FROM_03_30_TO_04_00(of(3,30)),
    FROM_04_00_TO_04_30(of(4,0)),
    FROM_04_30_TO_05_00(of(4,30)),
    FROM_05_00_TO_05_30(of(5,0)),
    FROM_05_30_TO_06_00(of(5,30)),
    FROM_06_00_TO_06_30(of(6,0)),
    FROM_06_30_TO_07_00(of(6,30)),
    FROM_07_00_TO_07_30(of(7,0)),
    FROM_07_30_TO_08_00(of(7,30)),
    FROM_08_00_TO_08_30(of(8,0)),
    FROM_08_30_TO_09_00(of(8,30)),
    FROM_09_00_TO_09_30(of(9,0)),
    FROM_09_30_TO_10_00(of(9,30)),
    FROM_10_00_TO_10_30(of(10,0)),
    FROM_10_30_TO_11_00(of(10,30)),
    FROM_11_00_TO_11_30(of(11,0)),
    FROM_11_30_TO_12_00(of(11,30)),
    FROM_12_00_TO_12_30(of(12,0)),
    FROM_12_30_TO_13_00(of(12,30)),
    FROM_13_00_TO_13_30(of(13,0)),
    FROM_13_30_TO_14_00(of(13,30)),
    FROM_14_00_TO_14_30(of(14,0)),
    FROM_14_30_TO_15_00(of(14,30)),
    FROM_15_00_TO_15_30(of(15,0)),
    FROM_15_30_TO_16_00(of(15,30)),
    FROM_16_00_TO_16_30(of(16,0)),
    FROM_16_30_TO_17_00(of(16,30)),
    FROM_17_00_TO_17_30(of(17,0)),
    FROM_17_30_TO_18_00(of(17,30)),
    FROM_18_00_TO_18_30(of(18,0)),
    FROM_18_30_TO_19_00(of(18,30)),
    FROM_19_00_TO_19_30(of(19,0)),
    FROM_19_30_TO_20_00(of(19,30)),
    FROM_20_00_TO_20_30(of(20,0)),
    FROM_20_30_TO_21_00(of(20,30)),
    FROM_21_00_TO_21_30(of(21,0)),
    FROM_21_30_TO_22_00(of(21,30)),
    FROM_22_00_TO_22_30(of(22,0)),
    FROM_22_30_TO_23_00(of(22,30)),
    FROM_23_00_TO_23_30(of(23,0)),
    FROM_23_30_TO_00_00(of(23,30));

    public final LocalTime time;

}
