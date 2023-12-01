package com.clientcrud.controller;

import java.util.List;

import com.clientcrud.domain.DomainEntity;

public class ReadAllController extends BaseController {
	public List<DomainEntity> exec(DomainEntity entity) {
		return facade.readAll(entity);
	}
}
