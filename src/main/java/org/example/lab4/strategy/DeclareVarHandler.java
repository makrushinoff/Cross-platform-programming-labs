package org.example.lab4.strategy;

import static org.example.lab4.data.Variables.byteArrayVariables;
import static org.example.lab4.data.Variables.byteVariables;
import static org.example.lab4.data.Variables.charArrayVariables;
import static org.example.lab4.data.Variables.charVariables;

import org.example.lab4.data.Command;
import org.example.lab4.exception.WrongSyntaxException;

public class DeclareVarHandler implements CommandHandler {

  @Override
  public Object handleCommand(String command) {
    String[] varDeclarationArgs = CommandHandler.getCommandArguments(command);
    if (varDeclarationArgs.length < 2) {
      throw new WrongSyntaxException("To declare new variable you need var name, var type, and, if it is array, array"
          + " length");
    }
    String varType = varDeclarationArgs[1];
    switch (varType) {
      case "byte":
        if (varDeclarationArgs.length != 2) {
          throw new WrongSyntaxException("Array size can not be applied to non-array type");
        }
        byteVariables.put(varDeclarationArgs[0], (byte) 0);
        break;
      case "char":
        if (varDeclarationArgs.length != 2) {
          throw new WrongSyntaxException("Array size can not be applied to non-array type");
        }
        charVariables.put(varDeclarationArgs[0], (char) 0);
        break;
      case "bytearr":
        byteArrayVariables.put(varDeclarationArgs[0], new Byte[Integer.parseInt(varDeclarationArgs[2])]);
        break;
      case "chararr":
        charArrayVariables.put(varDeclarationArgs[0], new Character[Integer.parseInt(varDeclarationArgs[2])]);
        break;
      default:
        throw new WrongSyntaxException("Wrong var type of value: " + varType);
    }
    return new Object();
  }

  @Override
  public Command getCommand() {
    return Command.DECLARE_VAR;
  }
}
