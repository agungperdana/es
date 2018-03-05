package com.kratonsolution.es.security.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Getter
@Entity
@Table(name="user")
public class User {
    
    @Id
    private String id = UUID.randomUUID().toString();
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Setter
    @Column
    private boolean enabled;
    
    @Version
    private Long version;
    
    User() {}
    
    public User(@NonNull String username, @NonNull String password) {
        
        this.username = username;
        this.password = password;
    }
    
    public void changePassword(@NonNull String newOne) {
        
        if(!this.password.equals(newOne)) {
            this.password = newOne;
        }
    }
}
