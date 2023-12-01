package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumGender;

public class Gender extends DomainEntity {
    private EnumGender name;
    
    public Gender() {}
    
    public Gender(EnumGender name) {
    	setName(name);
    }

    public EnumGender getName() {
        return name;
    }

    public void setName(EnumGender name) {
        this.name = name;
    }
}
