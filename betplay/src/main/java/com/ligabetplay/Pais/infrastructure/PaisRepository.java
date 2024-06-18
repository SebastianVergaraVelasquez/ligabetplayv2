package com.ligabetplay.Pais.infrastructure;

import com.ligabetplay.Pais.domain.models.Pais;
import java.util.List;
import java.util.Optional;

public interface PaisRepository {
    void save(Pais pais);
    boolean update(Pais pais);
    Optional<Pais> findById(int id);
    boolean delete(int id);
    List<Pais> findAll();
}
