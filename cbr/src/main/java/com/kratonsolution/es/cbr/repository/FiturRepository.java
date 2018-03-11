package com.kratonsolution.es.cbr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kratonsolution.es.cbr.model.Fitur;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface FiturRepository extends JpaRepository<Fitur, String> {
 
    public Optional<Fitur> findOneByName(@NonNull String name);
    
    @Query("FROM Fitur fit WHERE fit.name LIKE :key%")
    public List<Fitur> findAll(@Param("key") @NonNull String key);
    
    @Query("FROM Fitur fit WHERE fit.name LIKE :key%")
    public List<Fitur> findAll(Pageable pageable, @Param("key")@NonNull String key);
    
    @Query("SELECT COUNT(fitur) FROM Fitur fitur WHERE fitur.name LIKE :key%")
    public Long count(@Param("key") @NonNull String key);
}
