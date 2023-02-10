package org.example.lab2.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.lab2.controller.UserGUIController;
import org.example.lab2.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GUI extends Application {

    private static final Integer PRIMARY_SCENE_WIDTH = 400;
    private static final Integer PRIMARY_SCENE_HEIGHT = 300;

    private final UserGUIController controller = new UserGUIController();

    private Map<UUID, String> userMap = new HashMap<>();

    @Override
    public void start(Stage stage) throws Exception {
        this.setUpPrimaryStage(stage);
        stage.show();
    }

    private void setUpPrimaryStage(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        Button allUsersButton = new Button("View all users");
        Button createUserButton = new Button("Create user");
        allUsersButton.setOnAction(actionEvent -> {
            Stage userStage = this.setUpUsersStage(primaryStage);
            userStage.show();
            primaryStage.close();
        });
        createUserButton.setOnAction(actionEvent -> {
            Stage createUserStage = this.setUpCreateUserStage(primaryStage);
            createUserStage.show();
            primaryStage.close();
        });
        gridPane.add(new Text("Users"), 0, 0);
        gridPane.add(allUsersButton, 0, 1);
        gridPane.add(createUserButton, 0, 2);
        Scene scene = new Scene(gridPane, PRIMARY_SCENE_WIDTH, PRIMARY_SCENE_HEIGHT);
        primaryStage.setTitle("Lab 2 MVC");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
    }

    private Stage setUpCreateUserStage(Stage primaryStage) {
        GridPane createUserRoot = new GridPane();
        createUserRoot.setAlignment(Pos.CENTER);
        Stage createUserStage = new Stage();
        TextField firstName = new TextField();
        TextField lastName = new TextField();
        DatePicker birthDate = new DatePicker();
        TextField phoneNumber = new TextField();
        TextField email = new TextField();
        PasswordField password = new PasswordField();

        Button submit = new Button("Create user");

        createUserRoot.add(new Text("Create user"), 1, 0);
        createUserRoot.add(new Text("First name"), 0, 1);
        createUserRoot.add(firstName, 1, 1);
        createUserRoot.add(new Text("Last name"), 0, 2);
        createUserRoot.add(lastName, 1, 2);
        createUserRoot.add(new Text("Birth date"), 0, 3);
        createUserRoot.add(birthDate, 1, 3);
        createUserRoot.add(new Text("Phone number"), 0, 4);
        createUserRoot.add(phoneNumber, 1, 4);
        createUserRoot.add(new Text("Email"), 0, 5);
        createUserRoot.add(email, 1, 5);
        createUserRoot.add(new Text("Password"), 0, 6);
        createUserRoot.add(password, 1, 6);
        createUserRoot.add(submit, 1, 7);

        Scene scene = new Scene(createUserRoot, 600, 450);
        createUserStage.setTitle("Lab 2 MVC");
        createUserStage.setScene(scene);
        createUserStage.initStyle(StageStyle.DECORATED);

        submit.setOnAction(actionEvent -> {
            User user = User.builder()
                    .firstName(firstName.getText())
                    .lastName(lastName.getText())
                    .email(email.getText())
                    .birthDate(birthDate.getValue())
                    .phoneNumber(phoneNumber.getText())
                    .password(password.getText())
                    .build();
            try {
                controller.saveUser(user);
            } catch (Exception e) {
                Stage errorStage = this.setUpErrorStage(e.getMessage());
                errorStage.show();
            }
            createUserStage.close();
            primaryStage.show();
        });
        return createUserStage;
    }

    private Stage setUpUsersStage(Stage primaryStage) {
        GridPane usersRoot = new GridPane();
        usersRoot.setAlignment(Pos.CENTER);
        Stage usersStage = new Stage();
        Scene scene = new Scene(usersRoot, 600, 450);
        usersStage.setTitle("Lab 2 MVC");
        usersStage.setScene(scene);
        usersStage.initStyle(StageStyle.DECORATED);
        List<User> allUsers = controller.getAllUsers();
        int i = 0;
        while (i < allUsers.size()) {
            String userValue = this.getUserValue(allUsers.get(i));
            userMap.put(allUsers.get(i).getId(), userValue);
            Text userName = new Text(userValue);
            Button edit = new Button("Edit");
            Button delete = new Button("Delete");
            usersRoot.add(userName, 0, i);
            usersRoot.add(edit, 2, i);
            usersRoot.add(delete, 3, i);
            int finalI = i;
            delete.setOnAction(actionEvent -> {
                controller.deleteUser(allUsers.get(finalI).getId());
                usersRoot.getChildren().removeAll(userName, edit, delete);
            });

            edit.setOnAction(actionEvent -> {
                this.setUpUpdateUserStage(primaryStage, allUsers.get(finalI));
                usersStage.close();
            });
            i++;
        }
        Button getBack = new Button("Get back");
        getBack.setOnAction(actionEvent -> {
            usersStage.close();
            primaryStage.show();
        });
        usersRoot.add(getBack, 1, i);

        return usersStage;
    }

    private void setUpUpdateUserStage(Stage primaryStage, User userToUpdate) {
        GridPane updateUserRoot = new GridPane();
        updateUserRoot.setAlignment(Pos.CENTER);
        Stage updateUserStage = new Stage();
        TextField firstName = new TextField(userToUpdate.getFirstName());
        TextField lastName = new TextField(userToUpdate.getLastName());
        DatePicker birthDate = new DatePicker(userToUpdate.getBirthDate());
        TextField phoneNumber = new TextField(userToUpdate.getPhoneNumber());
        TextField email = new TextField(userToUpdate.getEmail());
        PasswordField password = new PasswordField();

        Button submit = new Button("Update user");

        updateUserRoot.add(new Text("Update user"), 1, 0);
        updateUserRoot.add(new Text("First name"), 0, 1);
        updateUserRoot.add(firstName, 1, 1);
        updateUserRoot.add(new Text("Last name"), 0, 2);
        updateUserRoot.add(lastName, 1, 2);
        updateUserRoot.add(new Text("Birth date"), 0, 3);
        updateUserRoot.add(birthDate, 1, 3);
        updateUserRoot.add(new Text("Phone number"), 0, 4);
        updateUserRoot.add(phoneNumber, 1, 4);
        updateUserRoot.add(new Text("Email"), 0, 5);
        updateUserRoot.add(email, 1, 5);
        updateUserRoot.add(new Text("Password"), 0, 6);
        updateUserRoot.add(password, 1, 6);
        updateUserRoot.add(submit, 1, 7);

        Scene scene = new Scene(updateUserRoot, 600, 450);
        updateUserStage.setTitle("Lab 2 MVC");
        updateUserStage.setScene(scene);
        updateUserStage.initStyle(StageStyle.DECORATED);

        submit.setOnAction(actionEvent -> {
            User user = User.builder()
                    .firstName(firstName.getText())
                    .lastName(lastName.getText())
                    .email(email.getText())
                    .birthDate(birthDate.getValue())
                    .phoneNumber(phoneNumber.getText())
                    .password(password.getText())
                    .build();
            try {
                controller.updateUser(userToUpdate.getId(), user);
                this.setUpUsersStage(primaryStage).show();
            } catch (Exception e) {
                Stage errorStage = this.setUpErrorStage(e.getMessage());
                errorStage.show();
            }
            updateUserStage.close();
        });
        updateUserStage.show();
    }

    private String getUserValue(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

    private Stage setUpErrorStage(String errorMessage) {
        VBox errorRoot = new VBox();
        errorRoot.setAlignment(Pos.CENTER);
        Stage errorStage = new Stage();
        Text text = new Text("Error: " + errorMessage);
        Button button = new Button("OK");
        errorRoot.getChildren().add(text);
        errorRoot.getChildren().add(button);
        Scene scene = new Scene(errorRoot, 300, 200);
        errorStage.setTitle("Lab 2 MVC");
        errorStage.setScene(scene);
        errorStage.initStyle(StageStyle.DECORATED);
        button.setOnAction(actionEvent -> {
            errorStage.close();
        });
        return errorStage;
    }

}
