package com.clientcrud.business;

import com.clientcrud.domain.DomainEntity;

public interface IStrategy {
    public String process(DomainEntity entity);
}
