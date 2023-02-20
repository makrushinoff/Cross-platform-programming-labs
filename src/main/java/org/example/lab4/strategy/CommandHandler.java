package org.example.lab4.strategy;

import org.example.lab4.data.Command;

public interface CommandHandler {

  Object handleCommand(String command);

  Command getCommand();

  static String[] getCommandArguments(String command) {
    return command.substring(9)
        .replace(" ", "")
        .split(",");
  }

  default boolean isNumber(String str) {
    try {
      Byte.parseByte(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
