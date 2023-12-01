package com.clientcrud.controller;

import com.clientcrud.domain.DomainEntity;

public class DeleteController extends BaseController {
	public String exec(DomainEntity entity) {
		return facade.delete(entity);
	}
}
