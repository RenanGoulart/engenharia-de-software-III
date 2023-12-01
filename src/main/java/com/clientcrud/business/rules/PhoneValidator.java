package com.clientcrud.business.rules;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Phone;

public class PhoneValidator implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof Phone) {
			Phone phone = (Phone) entity;
			StringBuilder resultMsg = new StringBuilder();
			
			return validateFields(phone, resultMsg);
		}
		return "Classe não corresponde\n";
	}
	
	private String validateFields(Phone phone, StringBuilder resultMsg) {
		if (phone.getDdd() == null || phone.getDdd().trim().equals("")) {
			resultMsg.append("DDD é um campo obrigatório\n");
		}
		if (phone.getNumber() == null || phone.getNumber().trim().equals("")) {
			resultMsg.append("Número é um campo obrigatório\n");
		}
		if (phone.getPhoneType().getName() == null) {
			resultMsg.append("Tipo de Telefone é um campo obrigatório\n");
		}
		
		if (resultMsg.length() > 0) {
			return resultMsg.toString();
		}
		return null;
	}

}
