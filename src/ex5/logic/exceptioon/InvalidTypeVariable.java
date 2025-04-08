package ex5.logic.exceptioon;

public class InvalidTypeVariable extends CompilationEror {
    private static final String message = "The type variable  in line \"%s\" is invalid.";
    private final String argName;
    private final int lineNumber;


    public InvalidTypeVariable(String argName, int linenumber) {
        super();
        this.argName = argName;
        this.lineNumber = linenumber;

    }
    @Override
    public String getMessage() {
        return super.getMessage() + String.format(message, lineNumber);
    }

}
