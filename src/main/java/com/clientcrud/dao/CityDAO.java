package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.City;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CityDAO implements IDAO {


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
    	ArrayList<DomainEntity> cities = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM cities;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                City city = new City();                
                city.setId(result.getInt("cit_id"));
                city.setName(result.getString("cit_name"));

                State state = new State();
                state.setId(result.getInt("cit_sta_id"));
                city.setState(state);
                
                cities.add(city);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return cities;
    }
}
