package com.clientcrud.domain;

public class City extends DomainEntity {
    private String name;
    private State state;
    
    public City() {}
    
    public City(int id) {
    	setId(id);
    }  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
