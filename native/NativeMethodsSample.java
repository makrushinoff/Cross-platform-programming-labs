public class NativeMethodsSample {

  static {
    System.loadLibrary("NumberHandler");
  }

  public static void main(String[] args) {
    System.out.println(NumberHandler.powIntTo2(20));
    System.out.println(NumberHandler.divideBy2Double(20.0));
    System.out.println(NumberHandler.addAuthorToString("20"));
    System.out.println(NumberHandler.subtract10fromFloat(20.0f));

  }

}
