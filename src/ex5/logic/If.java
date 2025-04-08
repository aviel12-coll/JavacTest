package ex5.logic;

import ex5.Regex.Regex;
import ex5.logic.exceptioon.InvalidCondition;
import ex5.logic.exceptioon.InvalidTypeVariable;
import ex5.logic.exceptioon.LineEror;
import javax.naming.InvalidNameException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class If extends expression {
   protected  String currentline;
    private int openBrace= 0;
    private Map<String,VariableType> values;
    private BufferedReader reader;
   private Methods method;
   private int lineNumber;
   private  Map<String,Methods> methods;


    If(Methods method) {
        super(method.getReader(), method.getCurrentLine());
        currentline = method.getCurrentLine();
        values = new HashMap<String,VariableType>();
        openBrace = 1;
        reader = method.getReader();
        this.method = method;
        methods= new HashMap<String, Methods>();
    }




    private  boolean checkPattern(String line ) {
        Pattern pattern = Pattern.compile("if\\(\\w+\\)\\{");
        String new_line=  line.replaceAll("\\s+", "");
        Matcher matcher = pattern.matcher(new_line);
        return matcher.matches();
    }


    private  boolean checkCondition(String condition) throws InvalidCondition {
        if (!Regex.matchPattern(Regex.VALID_CONDITION, condition)) {
            throw new InvalidCondition(condition, lineNumber);
        }
        return true;
    }




    @Override
    public boolean check()   {



        if (!Regex.matchPattern(Regex.CODE_BLOCK_CONDITION,currentline.trim())) {
            return false;
        }
        Matcher matcher = Regex.CODE_BLOCK_CONDITION.matcher(currentline);
        matcher.find();
        String condition = matcher.group(1);
        condition = condition.strip();
        try{
           if (!checkCondition(condition)){
               return false;
            }


        } catch (InvalidCondition e) {
            return false;
        }
        try{
            if (anlizeTheline())
            {
                method.previousLine=currentline;
                method.currentLine=reader.readLine();
                return true;
            }

        }
        catch (Exception e) {}




        return true;
    }

    private boolean anlizeTheline() throws LineEror, InvalidNameException, InvalidCondition, InvalidTypeVariable {


        try{

            while (currentline!=null) {

             method.previousLine= currentline;
            method.currentLine = reader.readLine();
            currentline = method.getCurrentLine();
            FileParser.numberLines++;


            LineType type= anylizeLIne.getLineType(currentline);

            if (type== LineType.EMPTY) {
                currentline = reader.readLine();
                FileParser.numberLines++;
            }
            if (type== LineType.METHOD_DECLERATION) {
                Methods nestedMethod= new Methods(reader, currentline);
                if (methods.containsValue(nestedMethod)) {
                    return false;
                }
               methods.put(nestedMethod.Extract_Name_Methods(), nestedMethod);
               return nestedMethod.check();
            }
            if (type==LineType.CONDITIONAL) {
                If nestedIf= new If(method);
                return nestedIf.check();
                }

            if (type==LineType.VAR_DECLERATION) {
                Variable variables= new Variable(reader, currentline);
                variables.check();
                for (String var : variables.getValue_of_the_line().keySet()){
                    if (values.containsKey(var)){
                        return false;
                    }
                    values.put(var, variables.getValue_of_the_line().get(var));
                }
                continue;
            }

           if (type== LineType.CLOSE_BLOCK) {
               openBrace--;
               if (openBrace==0){
                   return true;
               }
       }

        }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return false;
    }



    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }}
}
