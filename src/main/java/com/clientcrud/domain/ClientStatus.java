package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumClientStatus;

public class ClientStatus extends DomainEntity {
    private EnumClientStatus name;
    
    public ClientStatus() {}
    
    public ClientStatus(EnumClientStatus name) {
    	setName(name);
    }

    public EnumClientStatus getName() {
        return name;
    }

    public void setName(EnumClientStatus name) {
        this.name = name;
    }
}
