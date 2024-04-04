package com.driver.services;

import com.driver.model.User;

public interface UserService {
    void register(String name, String phoneNumber, String password);
    User updatePassword(Integer userId, String password);
    void deleteUser(Integer userId);
}
