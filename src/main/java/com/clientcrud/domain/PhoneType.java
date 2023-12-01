package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumPhoneType;

public class PhoneType extends DomainEntity {
    private EnumPhoneType name;
    
    public PhoneType() {}
    
    public PhoneType(EnumPhoneType name) {
    	setName(name);
    }

    public EnumPhoneType getName() {
        return name;
    }

    public void setName(EnumPhoneType name) {
        this.name = name;
    }
}
