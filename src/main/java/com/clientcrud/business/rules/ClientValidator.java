package com.clientcrud.business.rules;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.Client;
import com.clientcrud.domain.DomainEntity;

public class ClientValidator implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof Client) {
			Client client = (Client) entity;
			StringBuilder resultMsg = new StringBuilder();
			
			return validateFields(client, resultMsg);
		}
		return "Classe não corresponde";
	}
	
	private String validateFields(Client client, StringBuilder resultMsg) {
		if (client.getName() == null || client.getName().trim().equals("")) {
			resultMsg.append("Nome é um campo obrigatório\n");
		}
		if (client.getBirthDate() == null) {
			resultMsg.append("Data de nascimento é um campo obrigatório\n");
		}
		if (client.getCpf() == null || client.getCpf().trim().equals("")) {
			resultMsg.append("CPF é um campo obrigatório\n");
		}
		if (client.getEmail() == null || client.getEmail().trim().equals("")) {
			resultMsg.append("Email é um campo obrigatório\n");
		}
		if (client.getPassword() == null || client.getPassword().trim().equals("")) {
			resultMsg.append("Senha é um campo obrigatório\n");
		}
		
		PasswordValidator passwordValidator = new PasswordValidator();
		String resultPassword = passwordValidator.process(client);
		if (resultPassword != null) {
			resultMsg.append(resultPassword);
		}
		
		PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
		String resultEncryptor = passwordEncryptor.process(client);
		if (resultEncryptor != null) {
			resultMsg.append(resultEncryptor);
		}		
		
		if (client.getGender().getName() == null) {
			resultMsg.append("Gênero é um campo obrigatório\n");
		}
		if (client.getClientStatus().getName() == null) {
			resultMsg.append("Status é um campo obrigatório\n");
		}
		
		if (resultMsg.length() > 0) {
			return resultMsg.toString();
		}
		return null;
	}

}
