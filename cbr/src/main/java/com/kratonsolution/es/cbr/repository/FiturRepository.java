package com.kratonsolution.es.cbr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.es.cbr.model.Fitur;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface FiturRepository extends JpaRepository<Fitur, String> {
 
    public Optional<Fitur> findOneByName(@NonNull String name);
    
    public List<Fitur> findAllByNameLike(@NonNull String key);
    
    public List<Fitur> findAllByNameLike(Pageable pageable, @NonNull String key);
}
