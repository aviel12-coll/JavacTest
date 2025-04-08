package ex5.Regex;

import java.util.regex.Pattern;

public class Regex {
    /**
     * The constant COMMENT_LINE.
     */
    public static final Pattern COMMENT_LINE = Pattern.compile("^//");
    /**
     * The constant EMPTY_LINE.
     */
    public static final Pattern EMPTY_LINE = Pattern.compile("^\\s*$");
    /**
     * The constant VAR_DECLARATION_LINE.
     */

    public static final Pattern VARIABLE_DECLARATION_LINE = Pattern.compile("\\s*(?:final)?\\s*(int|double|String|boolean|char)\\s*(?:(?:([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)(?:\\s*=\\s*(?:\"[^\"]*\"|.+))?)\\s*,\\s*)*(?:([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)(?:\\s*=\\s*(?:\"[^\"]*\"|[^\\s,;]+))?)\\s*;\\s*$");
    /**
     * The constant METHOD_DECLARATION_LINE.
     */

    public static final Pattern METHOD_DECLARATION_LINE = Pattern.compile("\\s*void\\s+[A-Za-z]\\w*\\s*\\([^\\(\\)]*\\)");
    /**
    /**
     * The constant OPEN_BLOCK_LINE.
     */
    public static final Pattern OPEN_BLOCK_LINE = Pattern.compile("\\s*(?:if|while)");
    /**
     * The constant METHOD_CALL_LINE.
     */
    public static final Pattern METHOD_CALL_LINE = Pattern.compile("\\s*[A-Za-z]\\w*\\s*\\(");
    /**
     * The constant VAR_ASSIGNMENT_LINE.
     */
    public static final Pattern VARIABLE_ASSIGNMENT_LINE = Pattern.compile("\\s*(?:[A-Za-z]\\w*|_[A-Za-z0-9]\\w*)\\s*=");

    /**
     * The constant VAR_DECLARATION_TYPE.
     */
    public static final Pattern VAR_DECLARATION_TYPE = Pattern.compile("(?:(final)\\s+)?([^\\s]+)\\s+([^\\s]+)");
    /**
     * The constant VAR_DECLARATION_VALUES.
     */
    public static final Pattern VAR_DECLARATION_VALUES = Pattern.compile("([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)(?:\\s*(=)\\s*(\"[^\"]*\"|[^\\s,;]+))?");
    /**
     * The constant VAR_SETTING_VALUES.
     */
    public static final Pattern VAR_SETTING_VALUES = Pattern.compile("([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)(?:\\s*(?:=)\\s*([^\\s,;]+))");


    /**
     * The constant METHOD_DECLARATION.
     */
    public static final Pattern METHOD_DECLARATION = Pattern.compile("void\\s+([A-Za-z]\\w*)\\s*\\((.*)\\)\\s*\\{\\s*");
    /**
     * The constant METHOD_CALL.
     */
    public static final Pattern METHOD_CALL = Pattern.compile("([A-Za-z]\\w*)\\s*\\((.*)\\)");


    /**
     * The constant CODE_BLOCK_CONDITION.
     */
    public static final Pattern CODE_BLOCK_CONDITION = Pattern.compile("(?:if|while)\\s*\\(([^)]+)\\)\\s*\\{");


    /**
     * The constant VAR_NAME.
     */
    public static final Pattern VAR_NAME = Pattern.compile("([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)");
    /**
     * The constant METHOD_NAME.
     */
    public static final Pattern METHOD_NAME = Pattern.compile("\\s?[A-Za-z]\\w+\\s");
    /**
     * The constant PARAM_NAME.
     */
    public static final Pattern PARAM_NAME = Pattern.compile("(?:int|double|String|boolean|char)\\s+([A-Za-z]\\w*|_[A-Za-z0-9]\\w*)");


    /**
     * The constant CLOSE_BLOCK.
     */
    public static final Pattern CLOSE_BLOCK = Pattern.compile("\\s*}");
    /**
     * The constant SPLIT_CONDITION.
     */
    public static final String SPLIT_CONDITION = "&&|\\|\\|";
    /**
     * The constant VALID_CONDITION.
     */
    public static final Pattern VALID_CONDITION = Pattern.compile("(?:(true|false|(?:[A-Za-z]\\w*|_[A-Za-z0-9]\\w*)|(-?\\d+(?:\\.\\d*)?|\\.\\d+))\\s*(?:&&|\\|\\|)\\s*)*(true|false|(?:[A-Za-z]\\w*|_[A-Za-z0-9]\\w*)|(-?\\d+(?:\\.\\d*)?|\\.\\d+))");
    /**
     * The constant NUMBER.
     */
    public static final Pattern NUMBER = Pattern.compile("(?:-?\\d+(?:\\.\\d*)?|\\.\\d+)");
    /**
     * The constant BOOLEAN_VALUE.
     */
    public static final Pattern BOOLEAN_VALUE = Pattern.compile("true|false");
    /**
     * The constant RETURN.
     */
    public static final Pattern RETURN = Pattern.compile("\\s*return\\s*;\\s*$");

    /**
     * The constant INT_VALUE.
     */
    public static final Pattern INT_VALUE = Pattern.compile("-?\\d+");
    /**
     * The constant DOUBLE_VALUE.
     */
    public static final Pattern DOUBLE_VALUE = Pattern.compile("-?\\d+\\.\\d*|\\.\\d+");
    /**
     * The constant CHAR_VALUE.
     */
    public static final Pattern CHAR_VALUE = Pattern.compile("'.'");
    /**
     * The constant STRING_VALUE.
     */
    public static final Pattern STRING_VALUE = Pattern.compile("\".*\"");


    /**
     * The constant LINE_END_WITH_SEMICOLON.
     */
    public static final Pattern LINE_END_WITH_SEMICOLON = Pattern.compile("[^;]*;\\s*");
    /**
     * The constant LINE_END_WITH_BRACKET_OPEN.
     */
    public static final Pattern LINE_END_WITH_BRACKET_OPEN = Pattern.compile("[^\\{]*\\{\\s*");

    public static boolean matcherLine(Pattern pattern, String line) {
        return pattern.matcher((line)).lookingAt();
    }
    /**
     * Match pattern boolean.
     *
     * @param pattern the pattern
     * @param line    the line
     * @return the boolean
     */
    public static boolean matchPattern(Pattern pattern, String line) {
        return pattern.matcher((line)).matches();
    }





}
