package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LogDAO implements IDAO {

    @Override
    public void create(DomainEntity entity) {
    	try {
    		Log log = (Log) entity;
			Connection conn = ConnectionPool.getInstance().getConnection();
	        PreparedStatement stmt = conn.prepareStatement("INSERT INTO logs (log_datetime, log_changed_data, log_by) VALUES (?,?,?);");
	        
            stmt.setString(1, log.getCreatedAt().toString());
            stmt.setString(2, log.getChangedData());
            stmt.setString(3, "admin");
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao criar log");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entity) {

    }

    @Override
    public void delete(DomainEntity entity) {

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
