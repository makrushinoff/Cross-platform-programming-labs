package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class PrintHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] printArg = CommandHandler.getCommandArguments(command);
    String printVar = printArg[0];
    Character charVar = charVariables.get(printVar);
    if (charVar == null) {
      throw new WrongSyntaxException("Variables to print should exist and be 'char' type");
    }
    System.out.print(charVar);
    return new Object();
  }

  @Override
  public Command getCommand() {
    return Command.PRINT;
  }
}
