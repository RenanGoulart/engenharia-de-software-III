package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.Country;
import com.clientcrud.domain.DomainEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements IDAO {

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
    	ArrayList<DomainEntity> countries = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM countries;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Country country = new Country();
                country.setId(result.getInt("cou_id"));
                country.setName(result.getString("cou_name"));

                countries.add(country);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return countries;
    }
}
