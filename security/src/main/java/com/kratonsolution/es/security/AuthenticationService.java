package com.kratonsolution.es.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
	public UserDetails loadUserByUsername(@NonNull String name) throws UsernameNotFoundException
	{
		Optional<User> opt = service.findOneByUsername(name);
		Preconditions.checkState(opt.isPresent(), "User does not exist");
		
		return new SecurityInformation(opt.get(),new ArrayList<Authority>());
	}
}
