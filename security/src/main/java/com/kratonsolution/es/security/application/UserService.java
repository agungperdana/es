package com.kratonsolution.es.security.application;

import java.util.List;
import java.util.Optional;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.security.AuthenticationService;
import com.kratonsolution.es.security.SecurityInformation;
import com.kratonsolution.es.security.model.User;
import com.kratonsolution.es.security.repository.UserRepository;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserService {
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private AuthenticationService authService;
    
    private StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public void signIn(@NonNull String username, @NonNull String password) {
        
        Optional<User> opt = repo.findOneByUsername(username);
        
        Preconditions.checkState(opt.isPresent(), "User with name [%s] does not exist", username);
        Preconditions.checkState(encryptor.checkPassword(password, opt.get().getPassword()),"Login fail, username or password not match");
        
        SecurityInformation principal = authService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principal, opt.get().getPassword(), principal.getAuthorities());
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<User> getAllUsers(@NonNull String key) {
        return repo.findAllByUsernameLike(key);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<User> getAllUsers(int page, int size) {
        return repo.findAll(new PageRequest(page, size)).getContent();
    }
    
    @Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
    public List<User> getAllUsers(@NonNull String key,int page, int size) {
        return repo.findAllByUsernameLike(new PageRequest(page, size), key);
    }
    
    public Optional<User> getById(@NonNull String id) {
        
        return Optional.ofNullable(repo.findOne(id));
    }
    
    public void create(@NonNull User user) {
        
        Optional<User> opt = repo.findOneByUsername(user.getUsername());
        if(!opt.isPresent()) {
            repo.save(user);
        }
    }
    
    public void update(@NonNull User user) {
        
        Optional<User> opt = repo.findOneByUsername(user.getUsername());
        Preconditions.checkState(opt.isPresent(), "User does not exist.");
        opt.get().setEnabled(user.isEnabled());
        repo.save(opt.get());
    }
    
    public void enabled(@NonNull String username) {
        
        Optional<User> opt = repo.findOneByUsername(username);
        if(opt.isPresent() && !opt.get().isEnabled()) {
            
            opt.get().setEnabled(true);
            repo.save(opt.get());
        }
    }
    
    public void disabled(@NonNull String username) {
        
        Optional<User> opt = repo.findOneByUsername(username);
        if(opt.isPresent() && opt.get().isEnabled()) {
            
            opt.get().setEnabled(false);
            repo.save(opt.get());
        }
    }
    
    public boolean changePassword(@NonNull String username, @NonNull String oldPassword, @NonNull String newPassword) {

        Preconditions.checkState(oldPassword.equals(newPassword), "New password not equal current password.");
        
        Optional<User> opt = repo.findOneByUsername(username);
        if(opt.isPresent() && opt.get().isEnabled()) {
            
            if(encryptor.checkPassword(oldPassword, opt.get().getPassword())) {
                
                opt.get().changePassword(encryptor.encryptPassword(newPassword));
                repo.save(opt.get());
                
                return true;
            }
        }
        
        return false;
    }
    
    public void delete(@NonNull String id) {
        
        User user = repo.findOne(id);
        Preconditions.checkState(user != null, "User does not exist.");
        repo.delete(user);
    }
}
