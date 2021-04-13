package com.nt.niru.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nt.niru.util.JwtUtil;
@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil util;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//read the token from request using Auth key
		String token = request.getHeader("Authorization");
		if (token != null) {
			// do validation
			// get the subject name/username from token
			String tokenUsername = util.getSubject(token);
			// get all details Of User object based on Passed Username
			UserDetails urs = userDetailsService.loadUserByUsername(tokenUsername);
			// validate token
			boolean isValid = util.validateToken(token, urs.getUsername());
			// uname shoud not be null context should be null then only create the conext
			// auth object
			if (tokenUsername != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			}
			UsernamePasswordAuthenticationToken authenticationToken = new

			UsernamePasswordAuthenticationToken(tokenUsername, urs.getPassword(), urs.getAuthorities());
			//for this AuthToken link the current request
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			//now create SecurityContext Object
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		// if token is not valid then Dont create securityContext Object
		doFilter(request, response, filterChain);
	}

}
