package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a logical SQL expression that can be complex with nested AND/OR conditions.
 *
 * @author Colin Jokisch
 * @version 1.1
 */
@SuppressWarnings("unused")
public class LogicalGroup extends SqlExpression<LogicalGroup> {
    private final StringBuilder expression = new StringBuilder();
    // Private constructor to prevent direct instantiation
    private LogicalGroup() {}

    // Static factory method to initiate an expression with a given SqlExpression.
    public static LogicalGroup begin() {
        return new LogicalGroup();
    }

    // Adds a new group to the logical expression.
    public LogicalGroup groupAnd(SqlExpression<?>... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" AND "));

        this.expression.append("(")
                       .append(group)
                       .append(")");
        return this;
    }

    public LogicalGroup groupOr(SqlExpression<?>... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" OR "));

        this.expression.append("(")
                       .append(group)
                       .append(")");
        return this;
    }

    public LogicalGroup and(SqlExpression<?> expression, boolean wrapParentheses) {
        this.expression.append(" AND ")
                       .append(expression);
        if (wrapParentheses) {
            this.expression.insert(0, "(")
                           .append(")");
        }
        return this;
    }

    public LogicalGroup or(SqlExpression<?> expression, boolean wrapParentheses) {
        this.expression.append(" OR ")
                       .append(expression);
        if (wrapParentheses) {
            this.expression.insert(0, "(")
                           .append(")");
        }
        return this;
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public LogicalGroup self() {
        return this;
    }
}
