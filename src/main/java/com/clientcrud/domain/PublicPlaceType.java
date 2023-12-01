package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumPublicPlaceType;

public class PublicPlaceType extends DomainEntity {
    private EnumPublicPlaceType name;
    
    public PublicPlaceType(EnumPublicPlaceType name) {
    	setName(name);
    }
    
    public PublicPlaceType() {}

    public EnumPublicPlaceType getName() {
        return name;
    }

    public void setName(EnumPublicPlaceType name) {
        this.name = name;
    }
}
