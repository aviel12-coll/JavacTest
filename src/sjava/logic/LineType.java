package sjava.logic;

import java.util.concurrent.locks.Condition;

/**
 * The enum Line type.
 */
public enum LineType {
    /**
     * Comment line type.
     */
    COMMENT,
    /**
     * Empty line type.
     */
    EMPTY,
    /**
     * Var decleration line type.
     */
    VAR_DECLERATION,
    /**
     * Method decleration line type.
     */
    METHOD_DECLERATION,
    /**
     * Codeblock line type.
     */
    CODEBLOCK,
    /**
     * Method call line type.
     */
    METHOD_CALL,
    /**
     * Var assignment line type.
     */
    VARIABLE_ASSIGNMENT,
    /**
     * Return line type.
     */
    RETURN,
    /**
     * Close block line type.
     */
    CLOSE_BLOCK,
    /**
     * Unknown line type.
     */
    UNKNOWN,

    /**
     * if
     */
    CONDITIONAL

}
