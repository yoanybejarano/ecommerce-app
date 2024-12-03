package com.hatefulbug.ecommerce.service.user;


import com.hatefulbug.ecommerce.dto.UserDto;
import com.hatefulbug.ecommerce.model.User;
import com.hatefulbug.ecommerce.request.CreateUserRequest;
import com.hatefulbug.ecommerce.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);
    UserDto convertUserToDto(User user);
    User getAuthenticatedUser();
    boolean savePaymentUser(User user);

}
