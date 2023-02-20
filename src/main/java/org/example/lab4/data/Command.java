package org.example.lab4.data;

import java.util.Arrays;

public enum Command {
  START(0),
  DECLARE_VAR(1),
  TRANSFER_VALUE(2),
  LEFT_SHIFT(3),
  RIGHT_SHIFT(4),
  INCREMENT(5),
  ADD(6),
  SUBTRACT(7),
  MULTIPLY(8),
  DIVIDE(9),
  TO_CHAR(10),
  JUMP(11),
  PRINT(12),
  COPY_VALUE_FROM_ARRAY(13);

  private final Integer value;

  Command(Integer value) {
    this.value = value;
  }

  public static Command fromBinaryString(String binaryString) {
    int commandValue = Integer.parseInt(binaryString, 2);
    return Arrays.stream(values())
        .filter(command -> command.getValue().equals(commandValue))
        .findFirst()
        .orElse(null);
  }

  public Integer getValue() {
    return value;
  }
}
