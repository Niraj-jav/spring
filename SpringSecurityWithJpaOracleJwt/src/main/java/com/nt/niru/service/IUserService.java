package com.nt.niru.service;

import java.util.Optional;

import com.nt.niru.model.User;

public interface IUserService {
public Integer SaveUser(User user);
public Optional<User> findByusername(String username);
}
