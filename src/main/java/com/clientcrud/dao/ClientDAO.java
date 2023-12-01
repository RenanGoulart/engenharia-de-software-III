package com.clientcrud.dao;

import com.clientcrud.controller.CreateController;
import com.clientcrud.controller.DeleteController;
import com.clientcrud.controller.UpdateController;
import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.Address;
import com.clientcrud.domain.Client;
import com.clientcrud.domain.ClientStatus;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Gender;
import com.clientcrud.domain.Phone;
import com.clientcrud.domain.PhoneType;
import com.clientcrud.domain.enums.EnumClientStatus;
import com.clientcrud.domain.enums.EnumGender;
import com.clientcrud.domain.enums.EnumPhoneType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IDAO {

    @Override
    public void create(DomainEntity entity) {
    	try {
    		Client client = (Client) entity;
    		CreateController createController = new CreateController();
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO clients "
            		+ "(cli_name, cli_birth_date, cli_cpf, cli_email, cli_password, cli_gen_id, cli_sts_id) VALUES (?,?,?,?,?,?,?);", 
            		Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, client.getName());
            stmt.setDate(2, Date.valueOf(client.getBirthDate()));
            stmt.setString(3, client.getCpf());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPassword());
            stmt.setInt(6, client.getGender().getName().getValue());
            stmt.setInt(7, client.getClientStatus().getName().getValue());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir cliente");
            }
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                int clientId = generatedKeys.getInt(1);
                
                client.getPhone().setId(clientId);                
                createController.exec(client.getPhone());
                
                client.getCreditCard().setId(clientId);
                createController.exec(client.getCreditCard());
                
                for (Address address : client.getAddresses()) {
                	address.setId(clientId);
                	createController.exec(address);
                }
            } else {
                throw new SQLException("Falha ao obter o Id de cliente");
            }  
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entity) {
    	try {
    		Client client = (Client) entity;
    		UpdateController update = new UpdateController();
    		Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE clients "
            		+ "SET cli_name = ?, cli_birth_date = ?, cli_cpf = ?, cli_email = ?, cli_password = ?, cli_gen_id = ?, cli_sts_id = ? "
            		+ "WHERE cli_id = ?;");            
            stmt.setString(1, client.getName());
            stmt.setDate(2, Date.valueOf(client.getBirthDate()));
            stmt.setString(3, client.getCpf());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPassword());
            stmt.setInt(6, client.getGender().getName().getValue());
            stmt.setInt(7, client.getClientStatus().getName().getValue());
            stmt.setInt(8, client.getId()); 
            
            client.getPhone().setId(client.getId());            
            update.exec(client.getPhone());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao alterar clientes");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(DomainEntity entity) {
    	try {
    		Client client = (Client) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		DeleteController delete = new DeleteController();
    		
    		Phone phone = new Phone();
    		phone.setId(client.getId());
    		
    		delete.exec(phone);
    		delete.exec(client.getCreditCard());
    		
    		for (Address address : client.getAddresses()) {
    			delete.exec(address);
    		}
    		
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM clients WHERE clients.cli_id = ?;");
            stmt.setInt(1, client.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao excluir clientes");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
    	Client client = (Client) entity;

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = 
            		conn.prepareStatement("SELECT clients.*, phones.pho_ddd, phones.pho_number, phones.pho_pht_id FROM clients "
            				+ "JOIN phones ON clients.cli_id = phones.pho_cli_id "
            				+ "WHERE clients.cli_id = ?;");
            stmt.setInt(1, client.getId());
            
            ResultSet result = stmt.executeQuery();
                
            while (result.next()) {
                Phone phone = new Phone();
                
                client.setId(result.getInt("cli_id"));
                client.setName(result.getString("cli_name"));
                client.setBirthDate(result.getDate("cli_birth_date").toLocalDate());
                client.setCpf(result.getString("cli_cpf"));
                client.setEmail(result.getString("cli_email"));
                client.setPassword(result.getString("cli_password"));
                client.setGender(new Gender(EnumGender.fromValue(result.getInt("cli_gen_id"))));
                client.setClientStatus(new ClientStatus(EnumClientStatus.fromValue(result.getInt("cli_sts_id"))));
                
                phone.setDdd(result.getString("pho_ddd"));
                phone.setNumber(result.getString("pho_number"));
                phone.setPhoneType(new PhoneType(EnumPhoneType.fromValue(result.getInt("pho_pht_id"))));
                
                client.setPhone(phone);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return client;
    }

    @Override
    public List<DomainEntity> readAll(DomainEntity entity) {
    	ArrayList<DomainEntity> clients = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = 
            		conn.prepareStatement("SELECT clients.*, phones.pho_ddd, phones.pho_number, phones.pho_pht_id FROM clients "
            				+ "JOIN phones ON clients.cli_id = phones.pho_cli_id;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Client client = new Client();
                Phone phone = new Phone();
                
                client.setId(result.getInt("cli_id"));
                client.setName(result.getString("cli_name"));
                client.setBirthDate(result.getDate("cli_birth_date").toLocalDate());
                client.setCpf(result.getString("cli_cpf"));
                client.setEmail(result.getString("cli_email"));
                client.setPassword(result.getString("cli_password"));
                client.setGender(new Gender(EnumGender.fromValue(result.getInt("cli_gen_id"))));
                client.setClientStatus(new ClientStatus(EnumClientStatus.fromValue(result.getInt("cli_sts_id"))));
                
                phone.setDdd(result.getString("pho_ddd"));
                phone.setNumber(result.getString("pho_number"));
                phone.setPhoneType(new PhoneType(EnumPhoneType.fromValue(result.getInt("pho_pht_id"))));
                
                client.setPhone(phone);
                
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return clients;
    }
}
