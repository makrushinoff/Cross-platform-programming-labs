package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class JumpHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] jumpArgs = CommandHandler.getCommandArguments(command);
    String varValueToTrackString = jumpArgs[1];
    String targetValue = jumpArgs[2];
    Byte byteVarToTrack = byteVariables.get(varValueToTrackString);
    Character charVarToTrack = charVariables.get(varValueToTrackString);
    if (byteVarToTrack == null && charVarToTrack == null) {
      throw new WrongSyntaxException("Variable to track value should exist");
    }
    if (isNumber(targetValue)) {
      if ((byteVarToTrack != null && byteVarToTrack.equals(Byte.parseByte(targetValue))) ||
          (charVarToTrack != null && charVarToTrack.equals((char) (Integer.parseInt(String.valueOf(targetValue)))))) {
        return -1;
      }
      return Integer.parseInt(jumpArgs[0]);
    }
    Byte byteTargetVar = byteVariables.get(targetValue);
    Character charTargetVar = charVariables.get(targetValue);
    if (byteTargetVar == null && charTargetVar == null) {
      throw new WrongSyntaxException("Target variable should exist");
    }
    if ((byteVarToTrack != null && charTargetVar != null) ||
        (charVarToTrack != null && byteTargetVar != null)) {
      throw new WrongSyntaxException("target variable should be the same type with tracked variable");
    }
    if ((byteVarToTrack != null && byteVarToTrack.equals(byteTargetVar)) ||
        (charVarToTrack != null && charVarToTrack.equals(charTargetVar))) {
      return -1;
    }
    return Integer.parseInt(jumpArgs[0]);
  }

  @Override
  public Command getCommand() {
    return Command.JUMP;
  }
}
