package com.clientcrud.domain.enums;

public enum EnumGender {
    MASCULINO(1), FEMININO(2), NAO_INFORMADO(3);

    private int value;

    EnumGender(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static EnumGender fromValue(int value) {
        for (EnumGender type : EnumGender.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
