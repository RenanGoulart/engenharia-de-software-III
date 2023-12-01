package com.clientcrud.domain.enums;

public enum EnumPublicPlaceType {
    RUA(1), AVENIDA(2), ALAMEDA(3), ESTRADA(4), RODOVIA(5), OUTRO(6);

    private int value;

    EnumPublicPlaceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumPublicPlaceType fromValue(int value) {
        for (EnumPublicPlaceType type : EnumPublicPlaceType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
    
}
