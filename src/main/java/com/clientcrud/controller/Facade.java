package com.clientcrud.controller;

import com.clientcrud.business.IStrategy;
import com.clientcrud.business.rules.AddressValidator;
import com.clientcrud.business.rules.ClientValidator;
import com.clientcrud.business.rules.CreditCardValidator;
import com.clientcrud.business.rules.LogGenerator;
import com.clientcrud.business.rules.PhoneValidator;
import com.clientcrud.dao.*;
import com.clientcrud.domain.Address;
import com.clientcrud.domain.CardFlag;
import com.clientcrud.domain.City;
import com.clientcrud.domain.Client;
import com.clientcrud.domain.ClientStatus;
import com.clientcrud.domain.Country;
import com.clientcrud.domain.CreditCard;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Log;
import com.clientcrud.domain.Phone;
import com.clientcrud.domain.PhoneType;
import com.clientcrud.domain.PublicPlaceType;
import com.clientcrud.domain.ResidenceType;
import com.clientcrud.domain.State;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Facade implements IFacade{
	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> businessRules;
	
	public Facade() {
		daosDeclaration();
		businessRulesDeclaration();
	}
	
	private void daosDeclaration() {
		daos = new HashMap<String, IDAO>();
		daos.put(Address.class.getName(), new AddressDAO());
		daos.put(CardFlag.class.getName(), new CardFlagDAO());
		daos.put(City.class.getName(), new CityDAO());
		daos.put(Client.class.getName(), new ClientDAO());
		daos.put(ClientStatus.class.getName(), new ClientStatusDAO());
		daos.put(Country.class.getName(), new CountryDAO());
		daos.put(CreditCard.class.getName(), new CreditCardDAO());
		daos.put(Log.class.getName(), new LogDAO());
		daos.put(Phone.class.getName(), new PhoneDAO());
		daos.put(PhoneType.class.getName(), new PhoneTypeDAO());
		daos.put(PublicPlaceType.class.getName(), new PublicPlaceTypeDAO());
		daos.put(ResidenceType.class.getName(), new ResidenceTypeDAO());
		daos.put(State.class.getName(), new StateDAO());
	}
	
	private void businessRulesDeclaration() {
		businessRules = new HashMap<String, List<IStrategy>>();
		ClientValidator clientValidator = new ClientValidator();
		PhoneValidator phoneValidator = new PhoneValidator();
		AddressValidator addressValidator = new AddressValidator();
		CreditCardValidator creditCardValidator = new CreditCardValidator();
		LogGenerator logGenerator = new LogGenerator();
		
		List<IStrategy> clientRules = new ArrayList<IStrategy>();
		clientRules.add(logGenerator);
		clientRules.add(clientValidator);
		
		List<IStrategy> phoneRules = new ArrayList<IStrategy>();
		phoneRules.add(logGenerator);
		phoneRules.add(phoneValidator);
		
		List<IStrategy> addressRules = new ArrayList<IStrategy>();
		addressRules.add(logGenerator);
		addressRules.add(addressValidator);
		
		List<IStrategy> creditCardRules = new ArrayList<IStrategy>();
		creditCardRules.add(logGenerator);
		creditCardRules.add(creditCardValidator);
		
		businessRules.put(Client.class.getName(), clientRules);
		businessRules.put(Phone.class.getName(), phoneRules);
		businessRules.put(Address.class.getName(), addressRules);
		businessRules.put(CreditCard.class.getName(), creditCardRules);		
	}
	
	private String execRules(DomainEntity entity, String className) {
		List<IStrategy> entityRules = businessRules.get(className);
		StringBuilder resultString = new StringBuilder();
		
		for (IStrategy strategy : entityRules) {
			String msg = strategy.process(entity);
			if (msg != null) {
				resultString.append(msg+"\n");
			}
		}
		if(resultString.length() > 0) {
			return resultString.toString();
		} 
		return null;
	}

    @Override
    public String create(DomainEntity entity) {
    	String className = entity.getClass().getName();
    	String rulesResult = execRules(entity, className);
    	
    	if (rulesResult == null) {
    		IDAO dao = daos.get(className);
    		dao.create(entity);
    		return null;
    	}
    	return rulesResult;	
    }

    @Override
    public String update(DomainEntity entity) {
    	String className = entity.getClass().getName();
    	String rulesResult = execRules(entity, className);
    	
    	if (rulesResult == null) {
    		IDAO dao = daos.get(className);
    		dao.update(entity);
    		return null;
    	}
    	return rulesResult;	
    }

    @Override
    public String delete(DomainEntity entity) {
    	String className = entity.getClass().getName();
    	IDAO dao = daos.get(className);
    	
    	dao.delete(entity);
    	
    	return null;
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
    	String className = entity.getClass().getName();
    	IDAO dao = daos.get(className);
    	
    	return dao.read(entity);
    }

    @Override
    public List<DomainEntity> readAll(DomainEntity entity) {
    	String className = entity.getClass().getName();
    	IDAO dao = daos.get(className);
    	
    	return dao.readAll(entity);
    }
}
