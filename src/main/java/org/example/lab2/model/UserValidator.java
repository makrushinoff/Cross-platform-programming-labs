package org.example.lab2.model;

import java.time.LocalDate;

public class UserValidator {

    public void validateUser(User user) throws IllegalArgumentException{
        validateFieldsAreNotEmpty(user);
        validateEmail(user);
        validatePhoneNumber(user);
        validatePassword(user);
        validateBirthDate(user);
    }
    
    private void validateFieldsAreNotEmpty(User user) {
        if(user.getFirstName() == null || user.getFirstName().isEmpty() || user.getFirstName().isBlank()) {
            throw new IllegalArgumentException("First name should not be empty");
        }
        if(user.getLastName() == null || user.getLastName().isEmpty() || user.getLastName().isBlank()) {
            throw new IllegalArgumentException("Last name should not be empty");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email should not be empty");
        }

        if(user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty() || user.getPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Phone number should not be empty");
        }

        if(user.getPassword() == null || user.getPassword().isEmpty() || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password should not be empty");
        }

        if(user.getBirthDate() == null) {
            throw new IllegalArgumentException("Birth date should not be empty");
        }
        
    }

    private void validateEmail(User user) {
        String email = user.getEmail();
        if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new IllegalArgumentException("Email format is invalid");
        }
    }
    
    private void validateBirthDate(User user) {
        LocalDate birthDate = user.getBirthDate();
        LocalDate now = LocalDate.now();
        if(birthDate.isAfter(now)) {
            throw new IllegalArgumentException("Birth date can not be in future");
        }
    } 
    
    private void validatePassword(User user) {
        String password = user.getPassword();
        if(password.length() < 8) {
            throw new IllegalArgumentException("Password should be 8 or more characters");
        }
    }
    
    private void validatePhoneNumber(User user) {
        String phoneNumber = user.getPhoneNumber();
        if(!phoneNumber.startsWith("+")) {
            throw new IllegalArgumentException("Phone number is invalid");
        }
    }

}
