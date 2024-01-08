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

public class Between extends SqlExpression<Between> {
    private final StringBuilder expression = new StringBuilder();

    private Between(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange, boolean not) {
        this.expression.append(expression.render())
                       .append(not ? " NOT BETWEEN " : " BETWEEN ")
                       .append(startRange.render())
                       .append(" AND ")
                       .append(endRange.render());
    }

    public static Between between(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange) {
        return new Between(expression, startRange, endRange, false);
    }

    public static Between notBetween(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange) {
        return new Between(expression, startRange, endRange, true);
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public Between self() {
        return this;
    }
}

