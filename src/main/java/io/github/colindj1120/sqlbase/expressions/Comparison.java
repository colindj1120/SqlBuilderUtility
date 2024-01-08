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

/**
 * Class representing an SQL expression using operators.
 */

public class Comparison extends SqlExpression<Comparison> {
    private final SqlExpression<?> leftOperand;
    private final Operator         operator;
    private final SqlExpression<?> rightOperand;

    private Comparison(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        this.leftOperand  = leftOperand;
        this.operator     = operator;
        this.rightOperand = rightOperand;
    }

    public static Comparison comparison(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        return new Comparison(leftOperand, operator, rightOperand);
    }

    @Override
    public String render() {
        return String.format("%s %s %s", leftOperand.render(), operator.getSymbol(), rightOperand.render());
    }

    @Override
    public Comparison self() {
        return this;
    }
}

