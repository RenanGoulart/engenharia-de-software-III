package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.ResidenceType;
import com.clientcrud.domain.enums.EnumResidenceType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResidenceTypeDAO implements IDAO {


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
    	ArrayList<DomainEntity> residenceTypes = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM residence_types;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                ResidenceType residenceType = new ResidenceType();
                residenceType.setId(result.getInt("rtp_id"));
                residenceType.setName(EnumResidenceType.valueOf(result.getString("rtp_name")));

                residenceTypes.add(residenceType);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return residenceTypes;
    }
}
