package com.kratonsolution.es.security.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Document
public class User {
    
    private String username;
    
    private String password;
    
    @Setter
    private boolean enabled;
    
    public User(@NonNull String username, @NonNull String password) {
        
        this.username = username;
        this.password = password;
    }
}
