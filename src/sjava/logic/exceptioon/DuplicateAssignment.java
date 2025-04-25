package sjava.logic.exceptioon;

public class DuplicateAssignment extends RuntimeException {
    private static final String message = "Argument or the Methods %s seen on line %d was already defined.";
    private final String argName;
    private final int lineNumber;



    public DuplicateAssignment(String argName, int lineNumber) {
        super();
        this.argName = argName;
        this.lineNumber = lineNumber;
    }
    public String getMessage() {
        return super.getMessage() + String.format(message, argName, lineNumber);


}
}
