package sjava.logic;


import sjava.Regex.Regex;
import sjava.logic.exceptioon.InvalidTypeVariable;
import sjava.logic.exceptioon.LineEror;

import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Methods extends Objects {
    private static final String COMMA = ",";
    private static final int NEGATIVE_ONE = -1;
    private final Map<String, VariableType> map_values;
    private  BufferedReader reader;
    private String firstLIne;
    protected   String currentLine;
    protected String previousLine;
    private  int openBraces=0;

    private  Boolean HAS_RETURN = false;
    private  String methodName;
    private String VAR_WITH_THIS_NAME_ALREADY_DECLARED= "VAR_WITH_THIS_NAME_ALREADY_DECLARED";
    public Map<String, VariableType> getMap_values() {
        return map_values;
    }


    public int getOpenBraces() {
        return openBraces;
    }
    public String getCurrentLine() {
        return currentLine;
    }

    public BufferedReader getReader() {
        return reader;
    }



    Methods(BufferedReader in, String line) throws IOException {
        super(in, line);
        this.currentLine=line;
        this.reader = in;
        this.openBraces=1;
        this.map_values = new HashMap<String, VariableType>();

    }

    private boolean steelInFunction(String line)
    {
        Pattern pattern2= Pattern.compile("}");
        Matcher matcher2= pattern2.matcher(line);
        if(matcher2.find()){
            return openBraces != 0;
            }
        return true;

        }

    /**
     *
     * @return the name of the methods
     */

    public  String Extract_Name_Methods()
        {
            return methodName;
        }

    /**
     *
     * @return true if the method is correct
     * check the number of params, pattern of the Methods and all the line of the method
     * @throws LineEror
     * @throws InvalidNameException
     * @throws InvalidTypeVariable
     */



    @Override
    public boolean check() throws LineEror,  InvalidNameException, InvalidTypeVariable {
        Matcher matcher = Regex.METHOD_DECLARATION.matcher(currentLine);
        matcher.find();
        String methodName = matcher.group(1);
        this.methodName = methodName;
        String params = matcher.group(2);
            if (!check_pattern(currentLine)) {
                return false;
            }
            if (!check_name()) {
                return false;
            }
// Splits the params string by commas while preserving trailing empty values (if any),
// ensuring accurate parameter count and preventing unintended data loss.

        String[] params_list = Regex.matchPattern(Regex.EMPTY_LINE, params) ? new String[0]:
                params.split(COMMA, NEGATIVE_ONE);
        HashSet<String> varNamesSet = new HashSet<>();

        //check if the line is empty or  only with whitespace
        for (String param: params_list) {
            param = param.strip();
            if (Regex.matchPattern(Regex.EMPTY_LINE, param)) {
                throw new LineEror(FileParser.numberLines);
            }
        Matcher matcher2 = Regex.VAR_DECLARATION_TYPE.matcher(param);
            if (!matcher2.find()) {
                throw new LineEror(FileParser.numberLines);

            }
            boolean isFinal = matcher2.group(1) != null;
            String Type_of_the_var = matcher2.group(2);
            String name_of_the_parameter = param.substring(matcher2.end(2)+1);
            if (!check_name_param(param)) {
                return false;

            }
            matcher = Regex.VAR_NAME.matcher(param.substring(matcher2.end(2)+1));
            matcher.find();
            String varName = matcher.group(1);
            if (varNamesSet.contains(varName)) {
                throw new InvalidNameException(String.format(VAR_WITH_THIS_NAME_ALREADY_DECLARED, methodName, FileParser.numberLines));
            }
            varNamesSet.add(varName);
            map_values.put( name_of_the_parameter, VariableType.valueOf(Type_of_the_var.toUpperCase()));
        }
            //when i finsh to check the the first line
        try {
            while (true)
            {
                previousLine = currentLine;
                currentLine = reader.readLine();
                currentLine= currentLine.trim();
                FileParser.numberLines++;
                LineType type = anylizeLIne.getLineType(currentLine);
                if (type== LineType.CONDITIONAL){
                    If expressionIf= new If(this);
                    if(!expressionIf.check()){
                        return false;
                    }
                }
                if (type== LineType.VAR_DECLERATION){
                    Variable variables= new Variable(reader, currentLine);
                    variables.check();
                    for (String var : variables.getValue_of_the_line().keySet()){
                        if (!map_values.containsKey(var)){
                            return false;
                        }
                        map_values.put(var, variables.getValue_of_the_line().get(var));

                    }
                }
                if (type== LineType.RETURN)
                {
                    HAS_RETURN = true;
                }
                if (type== LineType.CLOSE_BLOCK){
                    if (HAS_RETURN){
                       return true;
                    }
                    return false;

                }


            }


        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * check the pattern of the methods
     * @param line
     * @return true if is matches
     */


    private boolean check_pattern(String line) {
//        String new_line=  line.replaceAll("\\s+", "");
//        Pattern pattern2= Pattern.compile("^void\\w+\\(\\w*(,\\w+)*\\)\\{");

        Pattern pattern2= Regex.METHOD_DECLARATION;
        Matcher matcher2= pattern2.matcher(line);
        if(matcher2.matches()){
            return true;
        }
        return false;


    }








    private Boolean check_name() {
       return Regex.METHOD_NAME.matcher(currentLine).find();
    }
    private Boolean check_name_param(String line) {
        return Regex.matchPattern(Regex.PARAM_NAME, line);
    }


    private void set_privious_line(String currentLine)
    {
        if (currentLine.startsWith("//")||currentLine.isBlank())
        {
            return;
        }
        previousLine=currentLine;

    }

}
