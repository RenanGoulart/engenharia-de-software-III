package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumResidenceType;

public class ResidenceType extends DomainEntity {
    private EnumResidenceType name;
    
    public ResidenceType(EnumResidenceType name) {
    	setName(name);
    }
    
    public ResidenceType() {}

    public EnumResidenceType getName() {
        return name;
    }

    public void setName(EnumResidenceType name) {
        this.name = name;
    }
}
