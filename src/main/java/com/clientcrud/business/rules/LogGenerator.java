package com.clientcrud.business.rules;

import java.time.LocalDateTime;

import com.clientcrud.business.IStrategy;
import com.clientcrud.dao.IDAO;
import com.clientcrud.dao.LogDAO;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Log;

public class LogGenerator implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		IDAO logDao = new LogDAO();
		Log log = new Log();
		
		log.setCreatedAt(LocalDateTime.now());
		log.setChangedData(entity.getClass().getName());
		log.setEntity(entity);
		
		logDao.create(log);
		return null;
	}

}
