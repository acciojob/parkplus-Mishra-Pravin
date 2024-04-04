
package com.driver.services.impl;

import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void register(String name, String phoneNumber, String password) {
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public User updatePassword(Integer userId, String password) {
        User user = userRepository.findById(userId);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }
}
