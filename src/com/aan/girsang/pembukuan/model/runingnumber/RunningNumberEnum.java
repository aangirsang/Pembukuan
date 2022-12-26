package com.aan.girsang.pembukuan.model.runingnumber;

public enum RunningNumberEnum {
    PESANAN("PSN", 4),
    BELANJA("JLA", 4);

    private final String id;
    private final Integer digit;

    private RunningNumberEnum(String id, Integer digit) {
        this.id = id;
        this.digit = digit;
    }

    public Integer getDigit() {
        return digit;
    }

    public String getId() {
        return id;
    }
}
