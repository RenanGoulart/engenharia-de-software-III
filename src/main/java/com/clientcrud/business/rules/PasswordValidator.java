package com.clientcrud.business.rules;

import com.clientcrud.business.IStrategy;
import com.clientcrud.domain.Client;
import com.clientcrud.domain.DomainEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern; 

public class PasswordValidator implements IStrategy {
	private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?~\\-]).{8,}$";	

	@Override
	public String process(DomainEntity entity) {
		if (entity instanceof Client) {
			String password = ((Client) entity).getPassword();
			
			if (isValidPassword(password)) {
				return null;
			}
			return "Senha não atende as restrições\n";
		}
		return "Classe não possuí atributo senha\n";
	}
	
	private boolean isValidPassword(String password) {
		Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
		Matcher passwordMatcher = passwordPattern.matcher(password);
		return passwordMatcher.matches();
	}
}
