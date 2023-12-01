package com.clientcrud.business.rules;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.CreditCard;
import com.clientcrud.domain.DomainEntity;

public class CreditCardValidator implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof CreditCard) {
			CreditCard creditCard = (CreditCard) entity;
			StringBuilder resultMsg = new StringBuilder();
			
			return validateFields(creditCard, resultMsg);
		}
		return "Classe não corresponde";
	}
	
	private String validateFields(CreditCard creditCard, StringBuilder resultMsg) {
		if (creditCard.getNumber() == null || creditCard.getNumber().trim().equals("")) {
			resultMsg.append("Número do Cartão é um campo obrigatório\n");
		}
		if (creditCard.getHolderName() == null || creditCard.getHolderName().trim().equals("")) {
			resultMsg.append("Nome Impresso é um campo obrigatório\n");
		}
		if (creditCard.getCvv() == null || creditCard.getCvv().trim().equals("")) {
			resultMsg.append("Código de Segurança é um campo obrigatório\n");
		}
		if (creditCard.getCardFlag().getName() == null) {
			resultMsg.append("Bandeira do Cartão é um campo obrigatório\n");
		}
		
		if (resultMsg.length() > 0) {
			return resultMsg.toString();
		}
		return null;
	}

}
