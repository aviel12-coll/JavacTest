package ex5.logic;

import ex5.Regex.Regex;
import ex5.logic.exceptioon.InvalidTypeVariable;
import ex5.logic.exceptioon.LineEror;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Variable extends Objects {
    private BufferedReader reader;
    public String currentline =null;
    private  int LineNumber;

    public Map<String, VariableType> getValue_of_the_line() {
        return Value_of_the_line;
    }



    Map<String,VariableType> Value_of_the_line= new HashMap<String, VariableType>();


    public Variable(BufferedReader br, String line) throws IOException {
        super(br, line);
        this.reader = br;
        this.currentline =line;
    }

    public boolean check() throws LineEror, InvalidTypeVariable {

        //check
        Matcher matcher = Regex.VAR_DECLARATION_TYPE.matcher(currentline);
        matcher.find();
        String typeString = matcher.group(2);
        VariableType type = VariableType.valueOf(typeString.toUpperCase());
        if (type == null) {

            throw new LineEror(LineNumber);
        }

        //remove the Type
        String line = currentline.substring(matcher.end(2));
        String name1 = matcher.group(3).trim();
        name1 = name1.replace(";", "");

        matcher = Regex.VAR_DECLARATION_VALUES.matcher(line);

        long count= -1;
        while (matcher.find()) {
            String name = matcher.group(1);
            count++;
            boolean isInitialized = matcher.group(2) != null;
            if (isInitialized) {
                String value = matcher.group(3);
                if (!check_value(type, value)) {
                    throw new InvalidTypeVariable(name, LineNumber);
                }


                Value_of_the_line.put(name,type);

                long commaCount = line.chars().filter(c -> c == ',').count();
                if (count != commaCount) {
                    throw new LineEror(LineNumber);
                }
        }


        }
        Value_of_the_line.put(name1,type);
//
//        String[] words = currentline.split("\\s+");
//
//        if (words.length == 2) {
//            words[1] = words[1].replace(";","");
//
//            return check_a_line_2_words(words);
//        }
        return true;



    }


    private boolean check_a_line_2_words(String[] words) {
        if (!check_type(words[0])) {
            System.out.println(1);
            return false;
        }

        if (words[1].endsWith(";")) {
            if (!check_name(words[1])) {
                System.out.println(1);
                return false;
            }
          ;
            Value_of_the_line.put(words[1], VariableType.valueOf(words[0].toUpperCase()));

            return true;
        }
        if (words[1].endsWith("=")) {
            System.out.println(10);
            //i need to continue
        }
        return true;




    }


    private boolean check_a_line_n_words(String[] words) {
        if (!check_type(words[0])) {
            System.out.println("Invalid type");
            return false;
        }

        // connect all the word
        String variablesPart = currentline.substring(words[0].length()).trim();

        // check if is finnish with ";"
        if (!variablesPart.endsWith(";")) {
            System.out.println("Line must end with a semicolon");
            return false;
        }
        // remove ";"
        variablesPart = variablesPart.substring(0, variablesPart.length() - 1).trim();

        // splite the line
        String[] variables = variablesPart.split(",");


        for (String variable : variables) {
            variable = variable.trim();

            // if there are =
            if (variable.contains("=")) {
                String[] parts = variable.split("=", 2);
                String varName = parts[0].trim();
                String value = parts[1].trim();
                Value_of_the_line.put(varName, VariableType.valueOf(words[0]));

                if (!check_name(varName)) {
                    System.out.println("Invalid variable name: " + varName);
                    return false;
                }
                // check if value is correct
                if (!check_value(VariableType.valueOf(words[0]),value)) {
                    System.out.println("Invalid value for variable: " + varName);
                    return false;
                }
                return true;
            } else {
                // אם אין השמה
                if (!check_name(variable)) {
                    System.out.println("Invalid variable name: " + variable);
                    return false;
                }




            }


        }


        return true;
    }

    public  boolean check_value(VariableType type,String value) {

        switch (type) {
            case INT:
                return Regex.matchPattern(Regex.INT_VALUE, value);
            case DOUBLE:

                return Regex.matchPattern(Regex.DOUBLE_VALUE, value);
            case STRING:


                    return Regex.matchPattern(Regex.STRING_VALUE, value);

            case BOOLEAN:
                return Regex.matchPattern(Regex.BOOLEAN_VALUE, value);

            case CHAR:

                return Regex.matchPattern(Regex.CHAR_VALUE, value);

        }
        return false;
    }


    private boolean check_type(String word){
        // Define the regex pattern to match the first word
        Pattern pattern = Pattern.compile("^(int|char|String|boolean|double)\\b");
        Matcher matcher = pattern.matcher(word);
        return matcher.find();

    }


    private boolean check_name(String word) {
        // Regex for first option: starts with a letter (a-zA-Z), not _ or digit
        Pattern pattern1 = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*[;]*");

        // Regex for second option: starts with _ and is followed by a letter (a-zA-Z)
        Pattern pattern2 = Pattern.compile("^_[a-zA-Z][a-zA-Z0-9_]*");

        // Check if the word matches either pattern
        Matcher matcher1 = pattern1.matcher(word);
        Matcher matcher2 = pattern2.matcher(word);

        return matcher1.matches() || matcher2.matches();
    }


}
