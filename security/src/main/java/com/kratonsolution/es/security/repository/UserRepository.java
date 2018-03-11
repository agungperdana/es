package com.kratonsolution.es.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kratonsolution.es.security.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface UserRepository extends JpaRepository<User, String> {
    
    public Optional<User> findOneByUsername(@NonNull String username);
    
    @Query("FROM User user WHERE user.username LIKE :key%")
    public List<User> findAll(@Param("key")@NonNull String key);
    
    @Query("FROM User user WHERE user.username LIKE :key%")
    public List<User> findAll(Pageable pageable,@Param("key")@NonNull String key);
    
    @Query("SELECT COUNT(user) FROM User user WHERE user.username LIKE :key%")
    public Long count(@Param("key") @NonNull String key);
}
