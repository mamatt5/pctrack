<<<<<<< HEAD
package com.arceus.SpringTemplate.security;
=======
package com.fdmgroup.PCTrack.security;
>>>>>>> SQLCheck

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

<<<<<<< HEAD
import com.arceus.SpringTemplate.model.User;
=======
import com.fdmgroup.PCTrack.model.User;
>>>>>>> SQLCheck

public class AuthUser implements org.springframework.security.core.userdetails.UserDetails{
	private User user;

	public AuthUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		System.out.println(new SimpleGrantedAuthority(this.user.getRole().toString()));
		return Arrays.asList(new SimpleGrantedAuthority(this.user.getRole().toString()));
=======
		//System.out.println(new SimpleGrantedAuthority(this.user.getRole().toString()));
		return Arrays.asList();
>>>>>>> SQLCheck
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
