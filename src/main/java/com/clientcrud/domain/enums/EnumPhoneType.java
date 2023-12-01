package com.clientcrud.domain.enums;

public enum EnumPhoneType {
    CELULAR(1), FIXO(2);

    private int value;

    EnumPhoneType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumPhoneType fromValue(int value) {
        for (EnumPhoneType type : EnumPhoneType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
