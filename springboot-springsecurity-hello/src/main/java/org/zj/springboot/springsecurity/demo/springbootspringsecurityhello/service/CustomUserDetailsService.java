package org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.model.Users;
import org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	UsersRepository usersRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		 Optional<Users> optionalUsers = usersRepository.findByUserName(username);
		 
		 optionalUsers
         	.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		 
		 Users user = optionalUsers.get();
		 Collection<GrantedAuthority> authorities = new HashSet<>();
		 
		 String role = "ROLE_ADMIN";
		 SimpleGrantedAuthority e = new SimpleGrantedAuthority(role);

		 authorities.add(e);
		 
		 return new User(user.getUserName(), user.getPassword(), authorities);
	}

}
