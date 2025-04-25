package sjava.logic.exceptioon;

public class InvalidCondition extends CompilationEror {
  private static final String message = "Condition %s seen on line %d is invalid.";
  private final String condition;
  private final int lineNumber;

  /**
   * Instantiates a new Invalid condition exception.
   *
   * @param condition  the condition
   * @param lineNumber the line number
   */
  public InvalidCondition(String condition, int lineNumber) {
    super();
    this.condition = condition;
    this.lineNumber = lineNumber;
  }

  @Override
  public String getMessage() {
    return super.getMessage() + String.format(message, condition, lineNumber);
  }
}

