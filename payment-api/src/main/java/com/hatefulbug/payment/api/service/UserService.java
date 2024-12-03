package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.request.PartialUser;

public interface UserService {

    User getUserByEmail(String email);
    User getUserById(int id);
    User createUser(PartialUser newUser);
    User updateUser(PartialUser user);
    User deleteUser(int id);

}
