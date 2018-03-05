package com.kratonsolution.es.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.es.security.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface UserRepository extends JpaRepository<User, String> {
    
    public Optional<User> findOneByUsername(@NonNull String username);
    
    public List<User> findAllByUsernameLike(@NonNull String key);
    
    public List<User> findAllByUsernameLike(Pageable pageable,@NonNull String key);
}
