package org.example.lab2;

import javafx.application.Application;
import org.example.lab2.view.Console;
import org.example.lab2.view.GUI;

public class MVCSample {
    public static void main(String[] args) {
//        Application.launch(GUI.class, args);
        new Console().startApplication();
    }
}
