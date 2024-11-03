package com.example.demo.service.user;

import com.example.demo.model.User;
import com.example.demo.request.AddUserRequest;
import com.example.demo.request.ChangePassword;

public interface UserServiceI {

    User getUserById(Long userId);
    User getUserByEmail(String email);
    User createUser(AddUserRequest request);
    User updateUser(ChangePassword request, Long userId);
    void deleteUser(Long userId);
    User getAuthenticatedUser();
}
