package com.example.junit5_example.repository;


import com.example.junit5_example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@DisplayName("UserRepository test class")
class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void beforeEach() {
        userRepository = new UserRepository();
    }


    @ParameterizedTest
    @MethodSource({"getSingleUser"})
    @DisplayName("test add() method")
    void addTest(User user) {
        userRepository.add(user);
        assertEquals(user.getId(), 1, "id of the user be equal 1");
    }


    @Test
    @DisplayName("test getById() method")
    void getIdTest() {
        User user = getSingleUser().get(0);
        userRepository.add(user);
        Optional<User> byId = userRepository.getById(user.getId());

        assumeTrue(byId.isPresent());

        assertSame(user, byId.get(), "these two references must be looking the same object");
    }

    @Test
    @DisplayName("test getAll() method")
    void getAllTest() {
        getSomeUsers().forEach(userRepository::add);
        List<User> all = userRepository.getAll();

        assertAll(
                () -> assertEquals(1, all.get(0).getId(), "first id of the user must be equal 1"),
                () -> assertEquals(2, all.get(1).getId(), "second id of the user must be equal 1"),
                () -> assertEquals(3, all.get(2).getId(), "thired id of the user must be equal 1"),
                () -> assertEquals(4, all.get(3).getId(), "fourt id of the user must be equal 1")
        );
    }

    static List<User> getSomeUsers() {
        return Arrays.asList(
                User.builder()
                        .name("John")
                        .surname("Travolta")
                        .age(55)
                        .build(),

                User.builder()
                        .name("Kianu")
                        .surname("Rives")
                        .age(49)
                        .build(),

                User.builder()
                        .name("Shonn")
                        .surname("Connori")
                        .age(74)
                        .build(),
                User.builder()
                        .name("Vin")
                        .surname("Diesel")
                        .age(52)
                        .build());
    }


    static List<User> getSingleUser() {
        return Collections.singletonList(User
                .builder()
                .name("Red")
                .surname("Tompson")
                .age(25)
                .build());
    }
}
