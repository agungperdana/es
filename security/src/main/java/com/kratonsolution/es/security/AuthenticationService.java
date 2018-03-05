package com.kratonsolution.es.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.kratonsolution.es.security.model.User;
import com.kratonsolution.es.security.repository.UserRepository;

import lombok.NonNull;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 */
@Service
public class AuthenticationService implements UserDetailsService
{
	@Autowired
	private UserRepository service;
	
	@Override
	public SecurityInformation loadUserByUsername(@NonNull String name) throws UsernameNotFoundException
	{
		Optional<User> opt = service.findOneByUsername(name);
		Preconditions.checkState(opt.isPresent(), "User does not exist");
		
		Collection<Authority> authoritys = new ArrayList<>();
		authoritys.add(new Authority("ROLE_USER"));
		authoritys.add(new Authority("ROLE_ADMIN"));
		
		return new SecurityInformation(opt.get(), authoritys);
	}
}
