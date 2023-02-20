package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class DivideHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] divideArgs = CommandHandler.getCommandArguments(command);
    String source = divideArgs[0];
    String product = divideArgs[1];
    Byte byteSource = byteVariables.get(source);
    Character charSource = charVariables.get(source);
    if (byteSource == null && charSource == null) {
      throw new WrongSyntaxException("Can not find source variable to divide value");
    }
    if (isNumber(product)) {
      if (byteSource != null) {
        byteVariables.merge(source, (byte) (byteSource / Byte.parseByte(product)), (before, after) -> after);
        return new Object();
      }
      char result = (char) (Integer.parseInt(product) / (int) charSource);
      charVariables.merge(source, result, (before, after) -> after);
      return new Object();
    }
    Byte byteProduct = byteVariables.get(product);
    Character charProduct = charVariables.get(product);
    if (byteProduct == null && charProduct == null) {
      throw new WrongSyntaxException("Can not find product variable to divide value");
    }
    if (byteProduct != null && byteSource != null) {
      byteVariables.merge(source, (byte) (byteSource / byteProduct), (before, after) -> after);
      return new Object();
    }
    if (charProduct != null && charSource != null) {
      char result = (char) (Integer.parseInt(String.valueOf(charProduct)) / (int) charSource);
      charVariables.merge(source, result, (before, after) -> after);
      return new Object();
    }
    throw new WrongSyntaxException("To divide variables they should exist and be the same type");
  }

  @Override
  public Command getCommand() {
    return Command.DIVIDE;
  }
}
