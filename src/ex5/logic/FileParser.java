package ex5.logic;

import ex5.Regex.Regex;
import ex5.logic.exceptioon.DuplicateAssignment;
import ex5.logic.exceptioon.InvalidTypeVariable;
import ex5.logic.exceptioon.LineEror;

import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class FileParser {
     private String filename;
     BufferedReader br;
     private String currentLine="";
    private HashMap<String, Methods> methods;
    private final ArrayList<HashMap<String,VariableType>> variableScopes;
    private HashMap<String, VariableType> GlobalVariable;
    public static int numberLines;



     public FileParser(String filename) {
         this.filename = filename;
         this.variableScopes= new ArrayList<>();
         this.GlobalVariable= new HashMap<>();
         this.methods= new HashMap<>();


     }


    /**
     * Reads the input file specified by the filename, stores the reader stream, and processes each line.
     *
     * This method uses a `BufferedReader` to read the file line by line.
     * For each line, it increments the line counter (`numberLines`) and processes the line using `parse_the_line()`.
     *
     * @throws IOException if an error occurs while opening or reading the file.
     * @throws LineEror if a structural error is found in the line during processing.
     * @throws InvalidNameException if an invalid name is detected in the file.
     * @throws InvalidTypeVariable if an invalid type variable is encountered in the line.
     */


     public void read() throws IOException, LineEror, InvalidNameException, InvalidTypeVariable {

         BufferedReader br = new BufferedReader(new FileReader(filename));
         this.br = br;
         while ((currentLine = br.readLine()) != null) {
             numberLines++;
             parse_the_line();
         }
     }

/**
 * Parses and processes a single line of code from the file.
 *
 * This method  determines the type  with using `anylizeLIne.getLineType(currentLine)`.
 * Depending on the line type, it performs different actions:
 * - If the line is a method declaration  it creates a `Methods` object,
 *   validates it, and adds it to the methods collection.
 * - If the line is a variable declaration  it creates a `Variable` object,
 *   validates it, and adds it to the variables collection.
 * - If the line is a variable assignment , it creates a `Variable` object
 *   and validates the assignment.
 *
 * The method reads lines iteratively, updating `currentLine` and incrementing `numberLines` as it processes them.
 *
 * @throws IOException if an error occurs while reading the file.
 * @throws LineEror if the line contains a structural error.
 * @throws InvalidNameException if an invalid name is detected in the line.
 */

     private  void parse_the_line() throws IOException, LineEror, InvalidNameException , InvalidTypeVariable {
         while (this.currentLine != null) {
             this.currentLine = this.currentLine.trim();
             LineType type = anylizeLIne.getLineType(currentLine);
             if (type == LineType.METHOD_DECLERATION) {
                 Methods methods= new Methods(br, currentLine);
                 if (!methods.check())
                 {
                     return;
                 }
                 add_methods(methods.Extract_Name_Methods(), methods);
             }
             if (type==LineType.VAR_DECLERATION  ){
                 Variable var= new Variable(br, currentLine);
                 var.check();
                 addTheVarible(var);
             }

             if (type==LineType.VARIABLE_ASSIGNMENT ){
                 Variable var= new Variable(br, currentLine);

                 check_assigment_variable(var);
                 return;

             }
             currentLine = br.readLine();
             numberLines++;
         }
     }

     public void add_methods(String methodName, Methods method)
     {
         methods.put(methodName, method);


     }

    /**
     * Validates a variable assignment.
     *
     * This method checks if a variable is correctly assigned a value according to its type.
     * It uses a regular expression  to extract variable names and values.
     *
     * The process follows these steps:
     * 1. Iterates over all matches found in the variable's `currentline`.
     * 2. Retrieves the variable name and its assigned value.
     * 3. Checks if the variable exists in `GlobalVariable`:
     *    - If the assigned value is also a variable, checks if their types match.
     *    - If the assigned value is a literal, validates it against the variable's type.
     * 4. Throws an `InvalidTypeVariable` exception if the assignment type is incorrect.
     * 5. Throws a `LineEror` exception if the variable does not exist in `GlobalVariable`.
     *
     * @param var The `Variable` object representing the assignment statement.
     * @return `true` if the assignment is valid.
     * @throws DuplicateAssignment if the variable is assigned a value multiple times.
     * @throws InvalidTypeVariable if the assigned value does not match the variable's expected type.
     * @throws LineEror if the variable does not exist or the assignment is malformed.
     */

     public boolean check_assigment_variable(Variable var) throws DuplicateAssignment, InvalidTypeVariable, LineEror {
         Matcher matcher = Regex.VAR_SETTING_VALUES.matcher(var.currentline);
         ArrayList<String> assignedVarName= new ArrayList<>();
         while (matcher.find()) {
             String varName = matcher.group(1);
             String varValue = matcher.group(2);
             if (GlobalVariable.containsKey(varName)) {
                 VariableType varType = GlobalVariable.get(varName);
                 if (GlobalVariable.containsKey(varValue)){
                     VariableType TY = GlobalVariable.get(varValue);
                     if (!(TY== varType)) {

                         throw new InvalidTypeVariable(varName,numberLines);
                     }
                     return true;
                 }
                 if (!var.check_value(varType, varValue)){
                     throw new InvalidTypeVariable(varName,numberLines);
                 }
                 return  true;
             }
             throw new LineEror(numberLines);

         }
         return true;

     }







    ///1. i want that the class vriable will check if line is ok
    /// i want to add the variable with the names in the arrays


    public void addTheVarible(Variable vars)
    {
        for ( String var : vars.Value_of_the_line.keySet())
        {
            GlobalVariable.put(var, vars.Value_of_the_line.get(var));
        }
    }



     }

