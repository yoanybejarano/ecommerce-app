package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.UserRepository;
import com.hatefulbug.payment.api.request.PartialUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private static User user = null;
    private static PartialUser partialUser = null;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setId(1);
        user.setFirstName("Issac");
        user.setLastName("Asimov");
        user.setPhoneNumber("111-111-1111");
        user.setEmail("asimov@email.com");

        partialUser = PartialUser.builder()
                .id((int) 1L)
                .email("asimov@email.com")
                .firstName("Issac")
                .lastName("Asimov")
                .phoneNumber("111-111-1111")
                .build();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);
        List<User> expected = userService.getUsers();
        assertEquals(expected, users);
    }

    @Test
    void getUserByEmail() {
        String email = "asimov@email.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        User expected = userService.getUserByEmail(email);
        assertThat(expected.getFirstName()).isSameAs(user.getFirstName());
        assertThat(expected.getLastName()).isSameAs(user.getLastName());
        assertThat(expected.getPhoneNumber()).isSameAs(user.getPhoneNumber());
        assertThat(expected.getEmail()).isSameAs(user.getEmail());
    }

    @Test
    void getUserById() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        User expected = userService.getUserById(user.getId());
        assertThat(expected.getFirstName()).isSameAs(user.getFirstName());
        assertThat(expected.getLastName()).isSameAs(user.getLastName());
        assertThat(expected.getPhoneNumber()).isSameAs(user.getPhoneNumber());
        assertThat(expected.getEmail()).isSameAs(user.getEmail());
    }

    @Test
    public void createUser() {
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User created = userService.createUser(partialUser);
        assertThat(created.getFirstName()).isSameAs(user.getFirstName());
        assertThat(created.getLastName()).isSameAs(user.getLastName());
        assertThat(created.getPhoneNumber()).isSameAs(user.getPhoneNumber());
        assertThat(created.getEmail()).isSameAs(user.getEmail());
    }

    @Test
    void updateUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        User updateUser = userService.updateUser(partialUser);
        assertThat(updateUser.getFirstName()).isSameAs(user.getFirstName());
        assertThat(updateUser.getLastName()).isSameAs(user.getLastName());
        assertThat(updateUser.getPhoneNumber()).isSameAs(user.getPhoneNumber());
        assertThat(updateUser.getEmail()).isSameAs(user.getEmail());
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(user.getId());
        userService.deleteUser(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
    }
}