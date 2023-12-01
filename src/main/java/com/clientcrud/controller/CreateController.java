package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

public class CreateController extends BaseController {
	public String exec(DomainEntity entity) {
		return facade.create(entity);
	}
}
