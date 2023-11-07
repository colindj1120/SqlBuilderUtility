package com.hyperion.sqlbuilder.datatypes.SqlStandard;

/**
 * Enum representing SQL operators.
 */
public enum Operator {
    EQUALS("="),
    NOT_EQUALS("<>"),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUAL(">="),
    LESS_THAN_OR_EQUAL("<="),
    AND("AND"),
    OR("OR"),
    LIKE("LIKE"),
    IN("IN"),
    NOT_IN("NOT IN"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    BETWEEN("BETWEEN"),
    NOT_BETWEEN("NOT BETWEEN"),
    EXISTS("EXISTS"),
    NOT_EXISTS("NOT EXISTS"),
    NOT("NOT");

    private final String symbol;

    Operator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

