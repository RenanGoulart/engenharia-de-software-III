package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.PublicPlaceType;
import com.clientcrud.domain.enums.EnumPublicPlaceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PublicPlaceTypeDAO implements IDAO {


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
    	ArrayList<DomainEntity> publicPlaceTypes = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM public_places_types;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                PublicPlaceType publicPlaceType = new PublicPlaceType();
                publicPlaceType.setId(result.getInt("ptp_id"));
                publicPlaceType.setName(EnumPublicPlaceType.valueOf(result.getString("ptp_name")));

                publicPlaceTypes.add(publicPlaceType);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return publicPlaceTypes;
    }
}
