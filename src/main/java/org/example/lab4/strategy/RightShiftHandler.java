package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class RightShiftHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] rightArgs = CommandHandler.getCommandArguments(command);
    String variableToShift = rightArgs[0];
    Byte byteVar = byteVariables.get(variableToShift);
    if (byteVar != null) {
      byteVar = (byte) (byteVar >> Integer.parseInt(rightArgs[1]));
      byteVariables.merge(variableToShift, byteVar, (before, after) -> after);
      return new Object();
    }

    Character charVar = charVariables.get(variableToShift);
    if (charVar != null) {
      int resultCharInt = Integer.parseInt(String.valueOf(charVar));
      resultCharInt = resultCharInt >> Integer.parseInt(rightArgs[1]);
      charVariables.merge(variableToShift, (char) resultCharInt, (before, after) -> after);
      return new Object();
    }
    throw new WrongSyntaxException("Can not right shift array type.");
  }

  @Override
  public Command getCommand() {
    return Command.RIGHT_SHIFT;
  }
}
