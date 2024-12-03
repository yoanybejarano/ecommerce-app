package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.UserRepository;
import com.hatefulbug.payment.api.request.PartialAuditLog;
import com.hatefulbug.payment.api.request.PartialUser;
import com.hatefulbug.payment.api.service.AuditLogService;
import com.hatefulbug.payment.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuditLogService logService;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("User with email %s not found", email)));
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("User with id %s not found", id)));
    }

    @Transactional
    @Override
    public User createUser(PartialUser newUser) {
        try {
            User user = new User();
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPhoneNumber(newUser.getPhoneNumber());
            User result = userRepository.save(user);
            logService.createAuditLog(PartialAuditLog.builder()
                    .userId(user.getId())
                    .action("User Created")
                    .details(String.format("User %s created successfully.", result.getId()))
                    .build());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public User updateUser(PartialUser user) {
        try {
            User existingUser = getUserById(user.getId());
            if (existingUser != null) {
                existingUser.setEmail(user.getEmail());
                existingUser.setFirstName(user.getFirstName());
                existingUser.setLastName(user.getLastName());
                existingUser.setPhoneNumber(user.getPhoneNumber());
                User result = userRepository.save(existingUser);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(user.getId())
                        .action("User Updated")
                        .details(String.format("User %s updated successfully.", result.getId()))
                        .build());
                return result;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public User deleteUser(int id) {
        try {
            User user = getUserById(id);
            if (user != null) {
                userRepository.delete(user);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(user.getId())
                        .action("User Deleted")
                        .details(String.format("User %s deleted successfully.", user.getId()))
                        .build());
                return user;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
