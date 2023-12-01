package com.clientcrud.business.rules;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.Address;
import com.clientcrud.domain.DomainEntity;

public class AddressValidator implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof Address) {
			Address address = (Address) entity;
			StringBuilder resultMsg = new StringBuilder();
			
			return validateFields(address, resultMsg);
		}
		return "Classe não corresponde";
	}

	
	private String validateFields(Address address, StringBuilder resultMsg) {
		if (address.getName() == null || address.getName().trim().equals("")) {
			resultMsg.append("Nome do Endereço é um campo obrigatório\n");
		}
		if (address.getPublicPlace() == null || address.getPublicPlace().trim().equals("")) {
			resultMsg.append("Logradouro é um campo obrigatório\n");
		}
		if (address.getNeighborhood() == null || address.getNeighborhood().trim().equals("")) {
			resultMsg.append("Bairro é um campo obrigatório\n");
		}
		if (address.getCep() == null || address.getCep().trim().equals("")) {
			resultMsg.append("CEP é um campo obrigatório\n");
		}
		if (address.getCity().getId() < 1) {
			resultMsg.append("Cidade é um campo obrigatório\n");
		}
		if (address.getPublicPlaceType().getName() == null) {
			resultMsg.append("Tipo de logradouro é um campo obrigatório\n");
		}
		if (address.getResidenceType().getName() == null) {
			resultMsg.append("Tipo de residência é um campo obrigatório\n");
		}
		if (address.getAddressType() == null) {
			resultMsg.append("Tipo de endereço é um campo obrigatório\n");
		}
		
		if (resultMsg.length() > 0) {
			return resultMsg.toString();
		}
		return null;
	}
}
