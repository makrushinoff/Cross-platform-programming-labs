package org.example.lab4;

import static org.example.lab4.data.Variables.byteVariables;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.lab4.data.Command;
import org.example.lab4.strategy.AddHandler;
import org.example.lab4.strategy.CommandHandler;
import org.example.lab4.strategy.CopyValueFromArrayHandler;
import org.example.lab4.strategy.DeclareVarHandler;
import org.example.lab4.strategy.DivideHandler;
import org.example.lab4.strategy.IncrementHandler;
import org.example.lab4.strategy.JumpHandler;
import org.example.lab4.strategy.LeftShiftHandler;
import org.example.lab4.strategy.MultiplyHandler;
import org.example.lab4.strategy.PrintHandler;
import org.example.lab4.strategy.RightShiftHandler;
import org.example.lab4.strategy.SubtractHandler;
import org.example.lab4.strategy.ToCharHandler;
import org.example.lab4.strategy.TransferValueHandler;

public class VMSample {

  public static final List<String> commands = new ArrayList<>();
  public static final Map<Command, CommandHandler> commandHandlers = new HashMap<>();

  static {
    commandHandlers.put(Command.ADD, new AddHandler());
    commandHandlers.put(Command.DECLARE_VAR, new DeclareVarHandler());
    commandHandlers.put(Command.DIVIDE, new DivideHandler());
    commandHandlers.put(Command.INCREMENT, new IncrementHandler());
    commandHandlers.put(Command.JUMP, new JumpHandler());
    commandHandlers.put(Command.LEFT_SHIFT, new LeftShiftHandler());
    commandHandlers.put(Command.RIGHT_SHIFT, new RightShiftHandler());
    commandHandlers.put(Command.MULTIPLY, new MultiplyHandler());
    commandHandlers.put(Command.PRINT, new PrintHandler());
    commandHandlers.put(Command.SUBTRACT, new SubtractHandler());
    commandHandlers.put(Command.TO_CHAR, new ToCharHandler());
    commandHandlers.put(Command.TRANSFER_VALUE, new TransferValueHandler());
    commandHandlers.put(Command.COPY_VALUE_FROM_ARRAY, new CopyValueFromArrayHandler());
  }

  public static void main(String[] args) throws IOException {
    List<String> strings = Files.readAllLines(Path.of("src", "main", "resources", "testVMFile.txt"))
        .stream()
        .filter(s -> !s.isBlank())
        .filter(s -> !s.startsWith("//"))
        .collect(Collectors.toList());
    int startIndex = 0;

    while (!strings.get(startIndex).startsWith(getByteIntString(Command.START.getValue()))) {
      startIndex++;
    }
    for (int i = startIndex; i < strings.size() - startIndex; i++) {
      commands.add(strings.get(i));
    }
    for (int i = 1; i < commands.size(); i++) {
      String commandString = commands.get(i).substring(0, 8);
      Command command = Command.fromBinaryString(commandString);
      CommandHandler commandHandler = commandHandlers.get(command);
      Object value = commandHandler.handleCommand(commands.get(i));
      if(command.equals(Command.JUMP)) {
        int intValue = (Integer) value;
        i = intValue == -1 ? i : intValue - 1;
      }
    }
  }

  private static String getByteIntString(Integer value) {
    String str = Integer.toString(value, 2);
    while (str.length() < 8) {
      str = "0".concat(str);
    }
    return str;
  }

}
