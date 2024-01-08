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

import io.github.colindj1120.sqlbase.enums.Operator;

public class IsNull extends SqlExpression<IsNull> {
    private final SqlExpression<?> expression;
    private final boolean          not;

    private IsNull(SqlExpression<?> expression, boolean not) {
        this.expression = expression;
        this.not        = not;
    }

    public static IsNull isNull(SqlExpression<?> expression) {
        return new IsNull(expression, false);
    }

    public static IsNull isNotNull(SqlExpression<?> expression) {
        return new IsNull(expression, true);
    }

    @Override
    public String render() {
        return String.format("%s %s", expression.render(), not ? Operator.IS_NOT_NULL.getSymbol() : Operator.IS_NULL.getSymbol());
    }

    @Override
    public IsNull self() {
        return this;
    }
}
