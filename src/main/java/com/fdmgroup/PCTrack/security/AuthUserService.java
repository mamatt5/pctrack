package com.arceus.SpringTemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arceus.SpringTemplate.dal.UserRepo;
import com.arceus.SpringTemplate.model.User;

@Service
public class AuthUserService implements org.springframework.security.core.userdetails.UserDetailsService{
	private UserRepo userRepo;

	@Autowired
	public AuthUserService(UserRepo userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepo.findByUsername(username).orElseThrow(
				()-> new UsernameNotFoundException(username));
		return new AuthUser(user);
	}
	
	

}
