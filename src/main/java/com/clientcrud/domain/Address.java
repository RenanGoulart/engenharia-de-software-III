package com.clientcrud.domain;

import com.clientcrud.domain.enums.EnumAddressType;

public class Address extends DomainEntity {
    private String name;
    private String publicPlace;
    private String number;
    private String neighborhood;
    private String cep;
    private String notes;
    private City city;
    private PublicPlaceType publicPlaceType;
    private ResidenceType residenceType;
    private EnumAddressType addressType;
    
    public Address() {}
    
    public Address(String name, String publicPlace, String number, String neighborhood, String cep, 
    		String notes, City city, PublicPlaceType publicPlaceType, ResidenceType residenceType, EnumAddressType addressType) {
    	setName(name);
    	setPublicPlace(publicPlace);
    	setNumber(number);
    	setNeighborhood(neighborhood);
    	setCep(cep);
    	setNotes(notes);
    	setCity(city);
    	setPublicPlaceType(publicPlaceType);
    	setResidenceType(residenceType);
    	setAddressType(addressType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public PublicPlaceType getPublicPlaceType() {
        return publicPlaceType;
    }

    public void setPublicPlaceType(PublicPlaceType publicPlaceType) {
        this.publicPlaceType = publicPlaceType;
    }

    public ResidenceType getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(ResidenceType residenceType) {
        this.residenceType = residenceType;
    }

	public EnumAddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(EnumAddressType addressType) {
		this.addressType = addressType;
	}   
    
}
