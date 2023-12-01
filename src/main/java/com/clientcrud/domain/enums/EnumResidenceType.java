package com.clientcrud.domain.enums;

public enum EnumResidenceType {
    CASA(1), APARTAMENTO(2), OUTRO(3);

    private int value;

    EnumResidenceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumResidenceType fromValue(int value) {
        for (EnumResidenceType type : EnumResidenceType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
