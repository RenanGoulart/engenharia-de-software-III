package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.CardFlag;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.enums.EnumCardFlag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CardFlagDAO implements IDAO {

    @Override
    public void create(DomainEntity entity) {

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
    	ArrayList<DomainEntity> cardFlags = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM card_flags;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                CardFlag cardFlag = new CardFlag();
                cardFlag.setId(result.getInt("cfl_id"));
                cardFlag.setName(EnumCardFlag.valueOf(result.getString("cfl_name")));

                cardFlags.add(cardFlag);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return cardFlags;
    }
}
