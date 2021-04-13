package com.nt.niru.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.niru.model.User;
import com.nt.niru.repo.UserRepository;
import com.nt.niru.service.IUserService;
@Service
public class UserService implements IUserService,UserDetailsService {
@Autowired
	private UserRepository repo;
@Autowired
private BCryptPasswordEncoder passwordEncode;
	@Override
	public Integer SaveUser(User user) {
		//Use password encode to save password in encode in user object
		user.setPwd(passwordEncode.encode(user.getPwd()));
		return repo.save(user).getId();
	}
	//get user by user username
	@Override
	public Optional<User> findByusername(String username) {
	
		return repo.findByusername(username);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//Get the User Model object
		Optional<User> opt= repo.findByusername(username);
		//read the username from 
		if(opt.isEmpty())
			throw new UsernameNotFoundException(username+" "+"Not Found");
		//read user from db
	User user=opt.get();
		//else return Spring Security User Object
		return new org.springframework.security.core.userdetails.User(username,
				user.getPwd(), 
				user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role))
				.collect(Collectors.toList()));
	}

}
