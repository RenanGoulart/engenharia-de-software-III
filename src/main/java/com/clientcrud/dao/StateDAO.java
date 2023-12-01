package com.clientcrud.dao;

import com.clientcrud.database.ConnectionPool;
import com.clientcrud.domain.Country;
import com.clientcrud.domain.DomainEntity;
import com.clientcrud.domain.State;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StateDAO implements IDAO {


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
    	ArrayList<DomainEntity> states = new ArrayList<>();

        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM states;");
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                State state = new State();
                state.setId(result.getInt("sta_id"));
                state.setName(result.getString("sta_name"));
                state.setUf(result.getString("sta_uf"));
                
                Country country = new Country();
                country.setId(result.getInt("sta_cou_id"));
                state.setCountry(country);

                states.add(state);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return states;
    }
}
