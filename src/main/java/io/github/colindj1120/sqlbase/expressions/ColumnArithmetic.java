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

public class ColumnArithmetic extends SqlExpression<ColumnArithmetic> {
    private final String           columnName;
    private final Operator         arithmeticOperation;
    private final SqlExpression<?> value;

    private ColumnArithmetic(String columnName, Operator arithmeticOperation, SqlExpression<?> value) {
        this.columnName          = columnName;
        this.arithmeticOperation = arithmeticOperation;
        this.value               = value;
    }

    public static ColumnArithmetic columnArithmetic(String columnName, Operator arithmeticOperation, SqlExpression<?> value) {
        return new ColumnArithmetic(columnName, arithmeticOperation, value);
    }

    @Override
    public String render() {
        return String.format("%s %s %s", columnName, arithmeticOperation.getSymbol(), value.render());
    }

    @Override
    public ColumnArithmetic self() {
        return this;
    }
}
