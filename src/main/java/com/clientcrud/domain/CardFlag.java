package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumCardFlag;

public class CardFlag extends DomainEntity {
    private EnumCardFlag name;
    
    public CardFlag() {}
    
    public CardFlag(EnumCardFlag name) {
    	setName(name);
    }

    public EnumCardFlag getName() {
        return name;
    }

    public void setName(EnumCardFlag name) {
        this.name = name;
    }
}
