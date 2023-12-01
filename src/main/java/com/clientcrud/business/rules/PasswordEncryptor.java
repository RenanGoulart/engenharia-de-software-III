package com.clientcrud.business.rules;

import org.mindrot.jbcrypt.BCrypt;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.Client;
import com.clientcrud.domain.DomainEntity;

public class PasswordEncryptor implements IStrategy {

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof Client) {
			Client client = (Client) entity;
			
			client.setPassword(BCrypt.hashpw(client.getPassword(), BCrypt.gensalt(10)));
			return null;
		}
		return "Classe não correspondente\n";
	}
}
