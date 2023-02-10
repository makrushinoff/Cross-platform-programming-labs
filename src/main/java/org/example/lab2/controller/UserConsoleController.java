package org.example.lab2.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.example.lab2.model.User;
import org.example.lab2.model.UserDto;
import org.example.lab2.model.UserService;

public class UserConsoleController {

  private final UserService userService = new UserService();

  public void saveUser(User user) {
    userService.createUser(user);
  }

  public void updateUser(UUID id, User user) {
    userService.updateUser(id, user);
  }

  public List<UserDto> getAllUsers(){
    return userService.getAllUsersEmails().stream()
        .map(this::mapUser)
        .collect(Collectors.toList());
  }

  private UserDto mapUser(User user) {
    return UserDto.builder()
        .fullName(user.getFirstName() + " " + user.getLastName())
        .id(user.getId())
        .build();
  }

  public User getUser(UUID id) {
    return userService.getUserById(id);
  }

  public void deleteUser(UUID id) {
    userService.deleteUser(id);
  }

}
