package com.kratonsolution.es.hamming.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kratonsolution.es.hamming.model.Solution;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface SolutionRepository extends JpaRepository<Solution, String> {

    @Query("FROM Solution solution WHERE solution.description LIKE :key%")
    public List<Solution> findAll(@Param("key") @NonNull String key);
    
    @Query("FROM Solution solution WHERE solution.description LIKE :key%")
    public List<Solution> findAll(Pageable pageable, @Param("key")@NonNull String key);
    
    @Query("SELECT COUNT(solution) FROM Solution solution WHERE solution.description LIKE :key%")
    public Long count(@Param("key") @NonNull String key);
}
