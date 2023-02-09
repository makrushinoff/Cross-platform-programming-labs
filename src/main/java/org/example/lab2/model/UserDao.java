package org.example.lab2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class UserDao {

    private static final List<User> userContainer = new ArrayList<>();

    {
        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("John")
                .lastName("Doe")
                .build());

        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("Michael")
                .lastName("Jackson")
                .build());

        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("Joe")
                .lastName("Biden")
                .build());

        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("Freddy")
                .lastName("Mercury")
                .build());

        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("Bred")
                .lastName("Pitt")
                .build());

        userContainer.add(User.builder()
                .id(UUID.randomUUID())
                .firstName("Margot")
                .lastName("Robbie")
                .build());
    }

    public void saveUser(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        userContainer.add(user);
    }

    public List<User> getAllUsers() {
        return userContainer;
    }

    public boolean userExistsById(UUID id) {
        return userContainer.stream()
                .anyMatch(user -> user.getId().equals(id));
    }

    public User getUserById(UUID id) {
        return userContainer.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can not find user with such id"));
    }

    public void deleteUser(UUID id) {
        userContainer.removeIf(user -> user.getId().equals(id));
    }

}
