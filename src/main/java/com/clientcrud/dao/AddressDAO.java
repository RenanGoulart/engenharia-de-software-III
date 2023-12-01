package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.Address;
import com.clientcrud.domain.City;
import com.clientcrud.domain.Country;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.PublicPlaceType;
import com.clientcrud.domain.ResidenceType;
import com.clientcrud.domain.State;
import com.clientcrud.domain.enums.EnumAddressType;
import com.clientcrud.domain.enums.EnumPublicPlaceType;
import com.clientcrud.domain.enums.EnumResidenceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO implements IDAO {

    @Override
    public void create(DomainEntity entity) {
    	try {
    		Address address = (Address) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO addresses "
            		+ "(adr_name, adr_public_place, adr_number, adr_neighborhood, adr_cep, adr_notes, adr_cli_id, adr_cit_id, adr_ptp_id, adr_rtp_id, adr_atp_id) "
            		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, address.getName());
            stmt.setString(2, address.getPublicPlace());
            stmt.setString(3, address.getNumber());
            stmt.setString(4, address.getNeighborhood());
            stmt.setString(5, address.getCep());
            stmt.setString(6, address.getNotes());
            stmt.setInt(7, address.getId());
            stmt.setInt(8, address.getCity().getId());
            stmt.setInt(9, address.getPublicPlaceType().getName().getValue());
            stmt.setInt(10, address.getResidenceType().getName().getValue());
            stmt.setInt(11, address.getAddressType().getValue());
            
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir endereço");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entity) {
    	try {
    		Address address = (Address) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("UPDATE addresses "
            		+ "SET adr_name = ?, adr_public_place = ?, adr_number = ?, adr_neighborhood = ?, adr_cep = ?, adr_notes = ?, "
            		+ "adr_cit_id = ?, adr_ptp_id = ?, adr_rtp_id = ?, adr_atp_id = ? "
            		+ "WHERE adr_id = ?;");
            stmt.setString(1, address.getName());
            stmt.setString(2, address.getPublicPlace());
            stmt.setString(3, address.getNumber());
            stmt.setString(4, address.getNeighborhood());
            stmt.setString(5, address.getCep());
            stmt.setString(6, address.getNotes());
            stmt.setInt(7, address.getCity().getId());
            stmt.setInt(8, address.getPublicPlaceType().getName().getValue());
            stmt.setInt(9, address.getResidenceType().getName().getValue());
            stmt.setInt(10, address.getAddressType().getValue());
            stmt.setInt(11, address.getId());
            
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao alterar endereço");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(DomainEntity entity) {
    	try {
    		Address address = (Address) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM addresses WHERE addresses.adr_id = ?;");
            stmt.setInt(1, address.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao excluir endereço");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
    	Address address = (Address) entity;

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT addresses.*, cities.cit_name, states.sta_name, countries.cou_name "
            		+ "FROM addresses "
            		+ "JOIN cities ON addresses.adr_cit_id = cities.cit_id "
            		+ "JOIN states ON cities.cit_sta_id  = states.sta_id "
            		+ "JOIN countries ON states.sta_cou_id  = countries.cou_id "
            		+ "WHERE addresses.adr_id = ?;");
            stmt.setInt(1, address.getId());
            
            ResultSet result = stmt.executeQuery();
                
            while (result.next()) {
            	City city = new City();
            	State state = new State();
            	Country country = new Country();
            	
            	address.setId(result.getInt("adr_id"));
            	address.setName(result.getString("adr_name"));
            	address.setPublicPlace(result.getString("adr_public_place"));
            	address.setNumber(result.getString("adr_number"));
            	address.setNeighborhood(result.getString("adr_neighborhood"));
            	address.setCep(result.getString("adr_cep"));
            	address.setNotes(result.getString("adr_notes"));
            	address.setPublicPlaceType(new PublicPlaceType(EnumPublicPlaceType.fromValue(result.getInt("adr_ptp_id"))));
            	address.setResidenceType(new ResidenceType(EnumResidenceType.fromValue(result.getInt("adr_rtp_id"))));
            	address.setAddressType(EnumAddressType.fromValue(result.getInt("adr_atp_id")));

            	country.setName(result.getString("cou_name"));
            	state.setName(result.getString("sta_name"));
            	city.setName(result.getString("cit_name"));
            	
            	state.setCountry(country);
            	city.setState(state);
            	address.setCity(city);
            } 
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return address;
    }

    @Override
    public List<DomainEntity> readAll(DomainEntity entity) {
    	ArrayList<DomainEntity> addresses = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT addresses.*, cities.cit_name, states.sta_uf, countries.cou_name "
            		+ "FROM addresses "
            		+ "JOIN cities ON addresses.adr_cit_id = cities.cit_id "
            		+ "JOIN states ON cities.cit_sta_id  = states.sta_id "
            		+ "JOIN countries ON states.sta_cou_id  = countries.cou_id "
            		+ "WHERE addresses.adr_cli_id = ?;");
            stmt.setInt(1, ((Address) entity).getId());
            
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
            	Address address = new Address();
            	City city = new City();
            	State state = new State();
            	Country country = new Country();
            	
            	address.setId(result.getInt("adr_id"));
            	address.setName(result.getString("adr_name"));
            	address.setPublicPlace(result.getString("adr_public_place"));
            	address.setNumber(result.getString("adr_number"));
            	address.setNeighborhood(result.getString("adr_neighborhood"));
            	address.setCep(result.getString("adr_cep"));
            	address.setNotes(result.getString("adr_notes"));
            	address.setPublicPlaceType(new PublicPlaceType(EnumPublicPlaceType.fromValue(result.getInt("adr_ptp_id"))));
            	address.setResidenceType(new ResidenceType(EnumResidenceType.fromValue(result.getInt("adr_rtp_id"))));
            	address.setAddressType(EnumAddressType.fromValue(result.getInt("adr_atp_id")));

            	country.setName(result.getString("cou_name"));
            	state.setUf(result.getString("sta_uf"));
            	city.setName(result.getString("cit_name"));
            	
            	state.setCountry(country);
            	city.setState(state);
            	address.setCity(city);
            	
            	addresses.add(address);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return addresses;
    }
}
