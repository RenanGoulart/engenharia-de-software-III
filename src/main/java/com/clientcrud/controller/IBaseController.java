package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

public interface IBaseController {
	public Object exec(DomainEntity entity);
}