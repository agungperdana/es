package com.kratonsolution.es.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kratonsolution.es.security.model.User;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
public interface UserRepository extends MongoRepository<User, String> {
    
    public Optional<User> findOneByUsername(@NonNull String username);
}
