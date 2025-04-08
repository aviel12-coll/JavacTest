package ex5.logic;

import ex5.Regex.Regex;

public class anylizeLIne {


    /**
     *Determines the type of a given line of code based on predefined regular expressions.
     * @param line
     * @return the correspondig Type of the line
     */

    public static LineType getLineType(String line) {
        if(Regex.matcherLine(Regex.COMMENT_LINE,line)){
            return LineType.COMMENT;
        }
        if(Regex.matcherLine(Regex.CLOSE_BLOCK,line)){
            return LineType.CLOSE_BLOCK;
        }
        if(Regex.matcherLine(Regex.RETURN,line)){
            return LineType.RETURN;
        }
        if (Regex.matcherLine(Regex.EMPTY_LINE,line)){
            return LineType.EMPTY;
        }
        if (Regex.matcherLine(Regex.VARIABLE_DECLARATION_LINE,line)){
            return LineType.VAR_DECLERATION;
        }
        if (Regex.matcherLine(Regex.METHOD_DECLARATION_LINE,line)) {
            return LineType.METHOD_DECLERATION;
        }
        if (Regex.matcherLine(Regex.OPEN_BLOCK_LINE,line)) {
            return LineType.CONDITIONAL;

        }
        if (Regex.matcherLine(Regex.METHOD_CALL_LINE,line)) {
            return LineType.METHOD_CALL;
        }
        if (Regex.matcherLine(Regex.VARIABLE_ASSIGNMENT_LINE,line)) {
        return LineType.VARIABLE_ASSIGNMENT;
        }

        if (Regex.matcherLine(Regex.OPEN_BLOCK_LINE ,line)) {
            return LineType.CONDITIONAL;
        }

        return LineType.UNKNOWN;


    }




}
