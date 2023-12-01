package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.enums.EnumPhoneType;
import com.clientcrud.domain.PhoneType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PhoneTypeDAO implements IDAO {


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
        ArrayList<DomainEntity> phoneTypes = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM phone_types;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                PhoneType phoneType = new PhoneType();
                phoneType.setId(result.getInt("pht_id"));
                phoneType.setName(EnumPhoneType.valueOf(result.getString("pht_name")));

                phoneTypes.add(phoneType);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return phoneTypes;
    }
}
