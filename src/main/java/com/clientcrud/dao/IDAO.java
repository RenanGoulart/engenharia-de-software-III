package com.clientcrud.dao;

import com.clientcrud.domain.DomainEntity;

import java.util.List;

public interface IDAO {
    public void create(DomainEntity entity);
    public void update(DomainEntity entity);
    public void delete(DomainEntity entity);
    public DomainEntity read(DomainEntity entity);
    public List<DomainEntity> readAll(DomainEntity entity);
}
