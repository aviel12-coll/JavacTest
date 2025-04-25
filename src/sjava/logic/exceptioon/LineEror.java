package sjava.logic.exceptioon;

public class LineEror extends CompilationEror {
  private static final String message = "Invalid line on line %d";
  private final int lineNumber;
      /**
       * Instantiates a new Invalid line exception.
       *
       * @param lineNumber the line number
       */
    public LineEror(int lineNumber) {
        super();
        this.lineNumber = lineNumber;
      }

      @Override
      public String getMessage() {
        return super.getMessage() + String.format(message, lineNumber);
      }
    }


