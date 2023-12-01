package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

public class UpdateController extends BaseController {
	public String exec(DomainEntity entity) {
		return facade.update(entity);
	}
}
