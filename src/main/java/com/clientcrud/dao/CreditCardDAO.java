package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.CardFlag;
import com.clientcrud.domain.CreditCard;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.enums.EnumCardFlag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAO implements IDAO {

    @Override
    public void create(DomainEntity entity) {
    	try {
    		CreditCard card = (CreditCard) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO credit_cards "
            		+ "(ccd_card_number, ccd_holder_name, ccd_security_code, ccd_is_main, ccd_cfl_id, ccd_cli_id) VALUES (?,?,?,?,?,?);");
            stmt.setString(1, card.getNumber());
            stmt.setString(2, card.getHolderName());
            stmt.setString(3, card.getCvv());
            stmt.setBoolean(4, card.getIsMain());
            stmt.setInt(5, card.getCardFlag().getName().getValue());
            stmt.setInt(6, card.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir cartão");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(DomainEntity entity) {
    	try {
    		CreditCard card = (CreditCard) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("UPDATE credit_cards "
            		+ "SET ccd_card_number = ?, ccd_holder_name = ?, ccd_security_code = ?, ccd_is_main = ?, ccd_cfl_id = ? "
            		+ "WHERE ccd_id = ?;");
            
            System.out.println("atributo: " + card.getId());
            
            stmt.setString(1, card.getNumber());
            stmt.setString(2, card.getHolderName());
            stmt.setString(3, card.getCvv());
            stmt.setBoolean(4, card.getIsMain());
            stmt.setInt(5, card.getCardFlag().getName().getValue());
            stmt.setInt(6, card.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao alterar cartão");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(DomainEntity entity) {
    	try {
    		CreditCard card = (CreditCard) entity;
    		Connection conn = ConnectionPool.getInstance().getConnection();
    		
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM credit_cards WHERE credit_cards.ccd_id = ?;");
            stmt.setInt(1, card.getId());
            
            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Falha ao excluir cartão");
            }            
     
    	} catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public DomainEntity read(DomainEntity entity) {
    	CreditCard card = (CreditCard) entity;

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = 
            		conn.prepareStatement("SELECT * FROM credit_cards WHERE credit_cards.ccd_id = ?;");
            stmt.setInt(1, card.getId());
            
            ResultSet result = stmt.executeQuery();
                
            while (result.next()) {
                card.setId(result.getInt("ccd_id"));
            	card.setNumber(result.getString("ccd_card_number"));
            	card.setHolderName(result.getString("ccd_holder_name"));
            	card.setCvv(result.getString("ccd_security_code"));
            	card.setIsMain(result.getBoolean("ccd_is_main"));
            	card.setCardFlag(new CardFlag(EnumCardFlag.fromValue(result.getInt("ccd_cfl_id"))));
            } 
            
        } catch (Exception e) {
            System.out.println(e);
        }

        return card;
    }

    @Override
    public List<DomainEntity> readAll(DomainEntity entity) {
    	ArrayList<DomainEntity> cards = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM credit_cards WHERE credit_cards.ccd_cli_id = ?;");
            stmt.setInt(1, ((CreditCard) entity).getId());
            
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
            	CreditCard card = new CreditCard();
            	card.setId(result.getInt("ccd_id"));
            	card.setNumber(result.getString("ccd_card_number"));
            	card.setHolderName(result.getString("ccd_holder_name"));
            	card.setCvv(result.getString("ccd_security_code"));
            	card.setIsMain(result.getBoolean("ccd_is_main"));
            	card.setCardFlag(new CardFlag(EnumCardFlag.fromValue(result.getInt("ccd_cfl_id"))));

                cards.add(card);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return cards;
    }
}
