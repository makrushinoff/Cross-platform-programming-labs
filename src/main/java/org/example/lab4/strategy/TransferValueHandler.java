package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteArrayVariables;
import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charArrayVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class TransferValueHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] transferArgs = CommandHandler.getCommandArguments(command);
    if (transferArgs.length < 2) {
      throw new WrongSyntaxException("To transfer values you need at least 2 arguments");
    }
    String sourceVarName = transferArgs[0];
    String targetValue = transferArgs[1];
    if (isNumber(targetValue)) {
      Byte byteSource = byteVariables.get(sourceVarName);
      if (byteSource != null) {
        byteVariables.merge(sourceVarName, Byte.valueOf(targetValue), (before, after) -> after);
        return new Object();
      }
      Character charSource = charVariables.get(sourceVarName);
      if (charSource != null) {
        charVariables.merge(sourceVarName, (char)Integer.parseInt(targetValue), (before, after) -> after);
        return new Object();
      }
      if (transferArgs.length == 2) {
        throw new WrongSyntaxException("Can not find variable to transfer value");
      }
      Byte[] byteArrayVar = byteArrayVariables.get(sourceVarName);
      if (byteArrayVar != null) {
        byteArrayVariables.get(sourceVarName)[Integer.parseInt(transferArgs[2])] = Byte.valueOf(targetValue);
        return new Object();
      }
      Character[] charArrayVar = charArrayVariables.get(sourceVarName);
      if (charArrayVar != null) {
        charArrayVariables.get(sourceVarName)[Integer.parseInt(transferArgs[2])] = (char) Integer.parseInt(targetValue);
        return new Object();
      }
      throw new WrongSyntaxException("Can not find variable to transfer value");
    }

    Byte byteTarget = byteVariables.get(targetValue);
    Byte byteSource = byteVariables.get(sourceVarName);
    if (byteTarget != null && byteSource != null) {
      byteVariables.merge(sourceVarName, byteTarget, (before, after) -> after);
      return new Object();
    }
    Character charTarget = charVariables.get(targetValue);
    Character charSource = charVariables.get(sourceVarName);
    if (charTarget != null && charSource != null) {
      charVariables.merge(sourceVarName, charTarget, (before, after) -> after);
      return new Object();
    }
    if (transferArgs.length == 2) {
      throw new WrongSyntaxException("Can not find variables to transfer value");
    }
    Byte[] byteArrayTarget = byteArrayVariables.get(targetValue);
    Byte[] byteArraySource = byteArrayVariables.get(sourceVarName);
    if (byteArrayTarget != null && byteArraySource != null) {
      byteArrayVariables.merge(sourceVarName, byteArrayTarget, (before, after) -> after);
      return new Object();
    }
    Character[] charArrayTarget = charArrayVariables.get(targetValue);
    Character[] charArraySource = charArrayVariables.get(sourceVarName);
    if (charArraySource != null && charArrayTarget != null) {
      charArrayVariables.merge(sourceVarName, charArrayTarget, (before, after) -> after);
      return new Object();
    }
    throw new WrongSyntaxException("Variables to transfer value should exist and be the same type");
  }

  @Override
  public Command getCommand() {
    return Command.TRANSFER_VALUE;
  }
}
