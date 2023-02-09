package org.example.lab2.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {

    private final UserValidator userValidator = new UserValidator();
    private final UserDao userDao = new UserDao();

    public void createUser(User user) throws IllegalArgumentException{
        userValidator.validateUser(user);
        userDao.saveUser(user);
    }

    public void updateUser(UUID id, User user) {
        if(!userDao.userExistsById(id)){
            throw new IllegalArgumentException("Can not find User with such id");
        }
        userDao.deleteUser(id);
        user.setId(id);
        userDao.saveUser(user);
    }

    public User getUserById(UUID id) {
        return userDao.getUserById(id);
    }

    public List<User> getAllUsersEmails() {
        return userDao.getAllUsers();
    }

    public void deleteUser(UUID id) {
        if(!userDao.userExistsById(id)){
            throw new IllegalArgumentException("Can not find User with such id");
        }
        userDao.deleteUser(id);
    }

}
