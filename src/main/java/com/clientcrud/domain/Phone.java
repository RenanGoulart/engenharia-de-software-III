package com.clientcrud.domain;

public class Phone extends DomainEntity {
    private String ddd;
    private String number;
    private PhoneType phoneType;
    
    public Phone() {}
    
    public Phone(String ddd, String number, PhoneType phoneType) {
    	setDdd(ddd);
    	setNumber(number);
    	setPhoneType(phoneType);
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(PhoneType phoneType) {
        this.phoneType = phoneType;
    }
}
