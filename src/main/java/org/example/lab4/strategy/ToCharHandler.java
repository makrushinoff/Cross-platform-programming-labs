package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class ToCharHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] toCharArg = CommandHandler.getCommandArguments(command);
    String source = toCharArg[0];
    Byte sourceByte = byteVariables.get(source);
    if (sourceByte == null) {
      throw new WrongSyntaxException("To char operation available only for exists variables of type byte");
    }
    byteVariables.remove(source, sourceByte);
    Character toChar = (char) sourceByte.byteValue();
    charVariables.put(source, toChar);
    return new Object();
  }

  @Override
  public Command getCommand() {
    return Command.TO_CHAR;
  }
}
