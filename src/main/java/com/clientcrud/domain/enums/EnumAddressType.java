package com.clientcrud.domain.enums;

public enum EnumAddressType {
    ENTREGA(1), COBRANCA(2);

    private int value;

    EnumAddressType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumAddressType fromValue(int value) {
        for (EnumAddressType type : EnumAddressType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}

