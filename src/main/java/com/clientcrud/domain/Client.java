package com.clientcrud.domain;

import java.time.LocalDate;
import java.util.List;

public class Client extends DomainEntity {
    private String name;
    private LocalDate birthDate;
    private String cpf;
    private String email;
    private String password;
    private Gender gender;
    private ClientStatus clientStatus;
    private Phone phone;
    private CreditCard creditCard;
    private List<Address> addresses;

    public Client() {}
    
    public Client(String name, LocalDate birthDate, String cpf, String email, String password,
    		Gender gender, ClientStatus clientStatus, Phone phone, CreditCard creditCard,
    		List<Address> addresses) {
    	setName(name);
    	setBirthDate(birthDate);
    	setCpf(cpf);
    	setEmail(email);
    	setPassword(password);
    	setGender(gender);
    	setClientStatus(clientStatus);
    	setPhone(phone);
    	setCreditCard(creditCard);
    	setAddresses(addresses);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ClientStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
