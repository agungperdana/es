package com.kratonsolution.es.cbr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kratonsolution.es.cbr.model.Kasus;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface KasusRepository extends JpaRepository<Kasus, String> {
 
    public Optional<Kasus> findOneByCode(@NonNull String code);
    
    @Query("FROM Kasus kasus WHERE code LIKE :key%")
    public List<Kasus> findAll(@Param("key") @NonNull String key);
    
    @Query("FROM Kasus kasus WHERE code LIKE :key%")
    public List<Kasus> findAll(Pageable pageable, @Param("key") @NonNull String key);
    
    @Query("SELECT COUNT(kasus) FROM Kasus kasus WHERE code LIKE :key%")
    public Long count(@Param("key") @NonNull String key);
}
