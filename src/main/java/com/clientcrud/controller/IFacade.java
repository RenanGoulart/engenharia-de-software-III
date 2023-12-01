package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

import java.util.List;

public interface IFacade {

    public String create(DomainEntity entity);
    public String update(DomainEntity entity);
    public String delete(DomainEntity entity);
    public DomainEntity read(DomainEntity entity);
    public List<DomainEntity> readAll(DomainEntity entity);
}
