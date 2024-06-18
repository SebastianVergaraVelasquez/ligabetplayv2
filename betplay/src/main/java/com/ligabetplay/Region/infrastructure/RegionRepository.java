package com.ligabetplay.Region.infrastructure;
import com.ligabetplay.Region.domain.models.Region;
import java.util.List;
import java.util.Optional;

public interface RegionRepository {

    void save (Region region);
    Optional<Region> findById (int id);
    boolean update (Region region);
    boolean delete (int id);
    List<Region> findAll();
}
