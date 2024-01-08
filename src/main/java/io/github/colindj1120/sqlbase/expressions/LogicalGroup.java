/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of SqlBuilderUtility (https://github.com/colindj1120/SqlBuilderUtility).
 *
 * SqlBuilderUtility is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SqlBuilderUtility is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SqlBuilderUtility.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.sqlbase.expressions;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a logical SQL expression that can be complex with nested AND/OR conditions.
 *
 * @author Colin Jokisch
 * @version 1.1
 */

public class LogicalGroup extends SqlExpression<LogicalGroup> {
    private final StringBuilder expression = new StringBuilder();

    // Private constructor to prevent direct instantiation
    private LogicalGroup() {}

    // Static factory method to initiate an expression with a given SqlExpression.
    public static LogicalGroup beginLogicalGroup() {
        return new LogicalGroup();
    }

    // Adds a new group to the logical expression.
    public LogicalGroup andGroup(SqlExpression<?>... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" AND "));

        this.expression.append("(")
                       .append(group)
                       .append(")");
        return this;
    }

    public LogicalGroup orGroup(SqlExpression<?>... expressions) {
        String group = Arrays.stream(expressions)
                             .map(SqlExpression::render)
                             .collect(Collectors.joining(" OR "));

        this.expression.append("(")
                       .append(group)
                       .append(")");
        return this;
    }

    public LogicalGroup expr(SqlExpression<?> expression, boolean wrapParentheses) {
        this.expression.append(expression.render());
        wrap(wrapParentheses);
        return this;
    }

    public LogicalGroup and(SqlExpression<?> expression, boolean wrapParentheses) {
        this.expression.append(" AND ")
                       .append(expression.render());
        wrap(wrapParentheses);
        return this;
    }

    public LogicalGroup or(SqlExpression<?> expression, boolean wrapParentheses) {
        this.expression.append(" OR ")
                       .append(expression.render());
        wrap(wrapParentheses);
        return this;
    }

    private void wrap(boolean wrapParentheses) {
        if (wrapParentheses) {
            this.expression.insert(0, "(")
                           .append(")");
        }
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
