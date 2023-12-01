package com.clientcrud.domain.enums;

public enum EnumCardFlag {
    VISA(1), MASTERCARD(2), ELO(3), AMERICAN_EXPRESS(4);

    private int value;

    EnumCardFlag(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumCardFlag fromValue(int value) {
        for (EnumCardFlag type : EnumCardFlag.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
