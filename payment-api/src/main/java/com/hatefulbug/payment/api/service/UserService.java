package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.request.PartialUser;

import java.util.List;

public interface UserService {

    List<User> getUsers();
    User getUserByEmail(String email);
    User getUserById(int id);
    User createUser(PartialUser newUser);
    User updateUser(PartialUser user);
    void deleteUser(int id);

}
