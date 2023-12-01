package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

public class ReadController extends BaseController {
	public DomainEntity exec(DomainEntity entity) {
		return facade.read(entity);
	}
}
