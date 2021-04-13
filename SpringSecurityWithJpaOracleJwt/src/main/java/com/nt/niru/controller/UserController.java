package com.nt.niru.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.niru.model.User;
import com.nt.niru.model.UserRequest;
import com.nt.niru.model.UserResponse;
import com.nt.niru.service.IUserService;
import com.nt.niru.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
	private IUserService servcie;
@Autowired
private JwtUtil util;
@Autowired
private AuthenticationManager authenticationManager;
//1.save user
@PostMapping("/save")
public ResponseEntity<String> saveUser(@RequestBody User user){
	Integer id=servcie.SaveUser(user);
	String body="Id"+id+" "+"is"+" "+"Saved";
	return new ResponseEntity<String>(body,HttpStatus.OK);
}
@PostMapping("/login")
public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest userRequest){
	//validate with databse uname and pass word then only generate token
	authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken
			(userRequest.getUsername(), userRequest.getPwd()));
	//if it is invalid then it triggers InvalidUserAuthenticationEntryPoint
	//generate token based on pass username
	String token=util.generateToken(userRequest.getUsername());
	//prepare response with token and message
	return ResponseEntity.ok(new UserResponse(token, "SUCCESS"));
}
//after sucessful login
@PostMapping("/welcome")
public ResponseEntity<String> accessData(Principal p){
	return ResponseEntity.ok("Hello User:"+p.getName());
	
}
}
