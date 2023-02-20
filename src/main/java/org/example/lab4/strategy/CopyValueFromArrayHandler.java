package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteArrayVariables;
import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charArrayVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class CopyValueFromArrayHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] commandArguments = CommandHandler.getCommandArguments(command);
    String arrayName = commandArguments[0];
    String indexName = commandArguments[1];
    String variableName = commandArguments[2];
    Byte[] byteArrayVar = byteArrayVariables.get(arrayName);
    Character[] charArrayVar = charArrayVariables.get(arrayName);
    int indexResult;
    if(isNumber(indexName)) {
      indexResult = Integer.parseInt(indexName);
    } else {
      Byte indexVar = byteVariables.get(indexName);
      if(indexVar == null) {
        throw new WrongSyntaxException("Variable for array index should exist and be type of byte");
      }
      indexResult = indexVar.intValue();
    }
    if(byteArrayVar == null && charArrayVar == null) {
      throw new WrongSyntaxException("First argument should be array");
    }
    if(byteArrayVar != null) {
      Byte byteVar = byteVariables.get(variableName);
      if(byteVar != null) {
        throw new WrongSyntaxException("Variable to put value from array should be the same type as array");
      }
      byteVariables.merge(variableName, byteArrayVar[indexResult], (before, after) -> after);
      return new Object();
    }
    Character charVar = charVariables.get(variableName);
    if(charVar == null) {
      throw new WrongSyntaxException("Variable to put value from array should be the same type as array");
    }
    charVariables.merge(variableName, charArrayVar[indexResult], (before, after) -> after);
    return new Object();
  }

  @Override
  public Command getCommand() {
    return Command.COPY_VALUE_FROM_ARRAY;
  }
}
