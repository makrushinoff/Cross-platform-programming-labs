package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class IncrementHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] incrementArg = CommandHandler.getCommandArguments(command);
    String variableToIncrement = incrementArg[0];
    Byte byteVar = byteVariables.get(variableToIncrement);
    if (byteVar != null) {
      byteVar++;
      byteVariables.merge(variableToIncrement, byteVar, (before, after) -> after);
      return new Object();
    }

    Character charVar = charVariables.get(variableToIncrement);
    if (charVar != null) {
      int resultCharInt = Integer.parseInt(String.valueOf(charVar));
      resultCharInt++;
      charVariables.merge(variableToIncrement, (char) resultCharInt, (before, after) -> after);
      return new Object();
    }
    throw new WrongSyntaxException("Can not increment array type.");  }

  @Override
  public Command getCommand() {
    return Command.INCREMENT;
  }
}
