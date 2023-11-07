package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a logical SQL expression that can be complex with nested AND/OR conditions.
 *
 * @version 1.1
 * @author Colin Jokisch
 */
public class LogicalExpression implements SqlExpression {
    private final StringBuilder expressionBuilder = new StringBuilder();

    // Private constructor to prevent direct instantiation
    private LogicalExpression() {}

    // Static factory method to initiate an expression with a given SqlExpression.
    public static LogicalExpression begin() {
        return new LogicalExpression();
    }

    // Adds a new group to the logical expression.
    public LogicalExpression groupAnd(SqlExpression... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" AND "));

        expressionBuilder.append("(").append(group).append(")");
        return this;
    }

    public LogicalExpression groupOr(SqlExpression... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" OR "));

        expressionBuilder.append("(").append(group).append(")");
        return this;
    }

    public LogicalExpression and(SqlExpression expression, boolean wrapParentheses) {
        expressionBuilder.append(" AND ").append(expression);
        if(wrapParentheses) {
            expressionBuilder.insert(0,"(").append(")");
        }
        return this;
    }

    public LogicalExpression or(SqlExpression expression, boolean wrapParentheses) {
        expressionBuilder.append(" OR ").append(expression);
        if(wrapParentheses) {
            expressionBuilder.insert(0,"(").append(")");
        }
        return this;
    }

    @Override
    public String render() {
        return expressionBuilder.toString();
    }
}
