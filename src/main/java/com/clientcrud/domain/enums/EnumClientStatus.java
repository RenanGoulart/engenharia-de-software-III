package com.clientcrud.domain.enums;

public enum EnumClientStatus {
    ATIVO(1), INATIVO(2);

    private int value;

    EnumClientStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumClientStatus fromValue(int value) {
        for (EnumClientStatus type : EnumClientStatus.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
