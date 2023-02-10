package org.example.lab2.controller;

import org.example.lab2.model.User;
import org.example.lab2.model.UserService;

import java.util.List;
import java.util.UUID;

public class UserGUIController {

    private final UserService userService = new UserService();

    public void saveUser(User user) {
        userService.createUser(user);
    }

    public void updateUser(UUID id, User user) {
        userService.updateUser(id, user);
    }

    public List<User> getAllUsers(){
        return userService.getAllUsersEmails();
    }

    public User getUser(UUID id) {
        return userService.getUserById(id);
    }

    public void deleteUser(UUID id) {
        userService.deleteUser(id);
    }

}
