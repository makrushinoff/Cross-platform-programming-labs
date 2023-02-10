package org.example.lab2.view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.example.lab2.controller.UserConsoleController;
import org.example.lab2.model.User;
import org.example.lab2.model.User.UserBuilder;
import org.example.lab2.model.UserDto;

public class Console {

  private static final Scanner SCANNER = new Scanner(System.in);
  private final UserConsoleController userConsoleController = new UserConsoleController();

  public void startApplication() {
    while (true) {
      printMenu();
      chooseMenuPoint();
    }
  }

  public void printMenu() {
    System.out.println("Menu:\n");
    System.out.println("1. View all users");
    System.out.println("2. Create new user");
    System.out.println("0. Exit\n");
    System.out.print("Enter the number of menu (1 or 2):");
  }

  private void chooseMenuPoint() {
    int menuPoint;
    do {
      menuPoint = SCANNER.nextInt();
    } while (!List.of(0, 1, 2).contains(menuPoint));

    switch (menuPoint) {
      case 0: {
        System.out.println("Bye bye!");
        System.exit(0);
      }
      case 1 : {
        this.handleUsersView();
        break;
      }
      case 2: {
        this.handleCreateUserView();
        break;
      }
      default:
        throw new IllegalArgumentException();
    }
  }

  private void handleCreateUserView() {
    boolean userIsValid = false;
    do {
      UserBuilder builder = User.builder();
      System.out.print("First name: ");
      SCANNER.nextLine();
      builder.firstName(SCANNER.nextLine());
      System.out.print("Last name: ");
      builder.lastName(SCANNER.nextLine());
      System.out.print("Email: ");
      builder.email(SCANNER.nextLine());
      System.out.print("Birth date: ");
      builder.birthDate(LocalDate.parse(SCANNER.nextLine()));
      System.out.print("Phone number: ");
      builder.phoneNumber(SCANNER.nextLine());
      System.out.print("Password: ");
      builder.password(SCANNER.nextLine());
      try {
        userConsoleController.saveUser(builder.build());
        userIsValid = true;
      } catch (IllegalArgumentException ignored) {
      }
    } while (!userIsValid);

  }

  private void handleUsersView() {
    boolean again = true;
    do {
      List<UserDto> users = userConsoleController.getAllUsers();
      printUsers(users);

      System.out.println("\nChoose action:");
      System.out.println("1. Edit user");
      System.out.println("2. Delete user");
      System.out.println("3. Go back to the main menu\n");
      System.out.print("Enter menu point (1, 2, or 3): ");
      again = this.chooseUsersViewMenuPoint(users);
    } while (again);
  }


  private void printUsers(List<UserDto> users) {
    for(int i = 0; i < users.size(); i++) {
      int order = i + 1;
      System.out.print(order);
      UserDto user = users.get(i);
      user.setOrder(order);
      System.out.println(". " + user.getFullName());
    }
  }

  private boolean chooseUsersViewMenuPoint(List<UserDto> users) {
    int menuPoint;
    do {
      menuPoint = SCANNER.nextInt();
    } while (!List.of(1, 2, 3).contains(menuPoint));

    switch (menuPoint) {
      case 1: {
        this.handleEditUser(users);
        break;
      }
      case 2 : {
        this.handleDeleteUser(users);
        break;
      }
      case 3: {
        return false;
      }
      default:
        throw new IllegalArgumentException();
    }
    return true;
  }

  private void handleDeleteUser(List<UserDto> users) {
    this.printUsers(users);
    System.out.print("Pick user (input the order number):");
    int userOrder = SCANNER.nextInt();
    UUID userId = users.stream()
        .filter(userDto -> userDto.getOrder() == userOrder)
        .findFirst()
        .orElseThrow()
        .getId();
    userConsoleController.deleteUser(userId);
  }

  private void handleEditUser(List<UserDto> users) {
    this.printUsers(users);
    System.out.print("Pick user (input the order number):");
    int userOrder = SCANNER.nextInt();
    UUID userId = users.stream()
        .filter(userDto -> userDto.getOrder() == userOrder)
        .findFirst()
        .orElseThrow()
        .getId();
    User user = userConsoleController.getUser(userId);
    printUserData(user);
    chooseAndEditUser(user);
    userConsoleController.updateUser(userId, user);
  }

  private void printUserData(User user) {
    System.out.println("1. First name: " + user.getFirstName());
    System.out.println("2. Last name: " + user.getLastName());
    System.out.println("3. Email: " + user.getEmail());
    System.out.println("4. Birthdate: " + user.getBirthDate());
    System.out.println("5. Phone number: " + user.getPhoneNumber());
    System.out.print("\nEnter option number: ");
  }

  private void chooseAndEditUser(User user) {
    int option;
    do {
      option = SCANNER.nextInt();
    } while (!List.of(1, 2, 3, 4, 5).contains(option));

    switch (option) {
      case 1: {
        String firstName = this.printAndGetFieldToChange("first name");
        user.setFirstName(firstName);
        break;
      }
      case 2 : {
        String lastName = this.printAndGetFieldToChange("last name");
        user.setLastName(lastName);
        break;
      }
      case 3: {
        String email = this.printAndGetFieldToChange("email");
        user.setEmail(email);
        break;
      }
      case 4: {
        String birthDate = this.printAndGetFieldToChange("birth date");
        user.setBirthDate(LocalDate.parse(birthDate));
        break;
      }
      case 5: {
        String phoneNumber = this.printAndGetFieldToChange("phone number");
        user.setPhoneNumber(phoneNumber);
        break;
      }
      default:
        throw new IllegalArgumentException();
    }
  }

  private String printAndGetFieldToChange(String fieldToChange) {
    System.out.print("Enter " + fieldToChange + ": ");
    SCANNER.nextLine();
    return SCANNER.nextLine();
  }

}
