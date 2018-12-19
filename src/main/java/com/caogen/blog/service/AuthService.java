package com.caogen.blog.service;

import com.caogen.blog.entity.User;

public interface AuthService {

    User register(User user);

    String login(String userName, String password);

    String refresh(String token);

}
