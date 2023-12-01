package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Phone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PhoneDAO implements IDAO {


    @Override
    public void create(DomainEntity entity) {
    	try {
    		Phone phone = (Phone) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO phones (pho_ddd, pho_number, pho_pht_id, pho_cli_id) VALUES (?,?,?,?);");
            stmt.setString(1, phone.getDdd());
            stmt.setString(2, phone.getNumber());
            stmt.setInt(3, phone.getPhoneType().getName().getValue());
            stmt.setInt(4, phone.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir telefone");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entity) {
    	try {
    		Phone phone = (Phone) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("UPDATE phones SET pho_ddd = ?, pho_number = ?, pho_pht_id = ? "
            		+ "WHERE pho_cli_id = ?;");
            stmt.setString(1, phone.getDdd());
            stmt.setString(2, phone.getNumber());
            stmt.setInt(3, phone.getPhoneType().getName().getValue());
            stmt.setInt(4, phone.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao alterar telefone");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(DomainEntity entity) {
    	try {
    		Phone phone = (Phone) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM phones WHERE phones.pho_cli_id = ?;");
            stmt.setInt(1, phone.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao excluir telefone");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
        return null;
    }

    @Override
    public List<DomainEntity> readAll(DomainEntity entity) {
        return null;
    }
}
