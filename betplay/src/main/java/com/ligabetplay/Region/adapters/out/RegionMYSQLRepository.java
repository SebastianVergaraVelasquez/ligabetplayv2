package com.ligabetplay.Region.adapters.out;

import java.util.List;
import java.util.Optional;

import com.ligabetplay.Region.domain.models.Region;
import com.ligabetplay.Region.infrastructure.RegionRepository;

public class RegionMYSQLRepository implements RegionRepository{

    private final String url;
    private final String user;
    private final String password;

    public RegionMYSQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Region region) {
        
    }

    @Override
    public Optional<Region> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public boolean update(Region region) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Region> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
