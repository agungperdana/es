package com.kratonsolution.es.cbr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.es.cbr.model.Kasus;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface KasusRepository extends JpaRepository<Kasus, String> {
 
    public Optional<Kasus> findOneByCode(@NonNull String code);
    
    public List<Kasus> findAllByCodeLike(@NonNull String key);
    
    public List<Kasus> findAllByCodeLike(Pageable pageable, @NonNull String key);
}
