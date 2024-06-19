package com.ligabetplay.Region.application;

import com.ligabetplay.Region.infrastructure.RegionRepository;

import java.util.Optional;
import java.util.List;
import com.ligabetplay.Region.domain.models.Region;

public class RegionService {

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public void createRegion(Region region) {
        regionRepository.save(region);
    }

    public boolean updateRegion(Region region) {
        return regionRepository.update(region);
    }

    public boolean deleteRegion(int id){
        return regionRepository.delete(id);
    }

    public Optional<Region> getRegionById(int id){
        return regionRepository.findById(id);
    }

    public List<Region> getAllRegiones (){
        return regionRepository.findAll();
    }
}
