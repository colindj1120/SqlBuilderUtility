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
import io.github.colindj1120.sqlbase.enums.Quantifier;

/**
 * Represents a qualified comparison expression with SQL quantifiers.
 */
public class QualifiedComparison extends SqlExpression<QualifiedComparison> {
    private final SqlExpression<?> left;
    private final Operator         operator;
    private final Quantifier       quantifier;
    private final Subquery         subquery;

    private QualifiedComparison(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        this.left       = left;
        this.operator   = operator;
        this.quantifier = quantifier;
        this.subquery   = subquery;
    }

    public static QualifiedComparison qualifiedComparison(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        return new QualifiedComparison(left, operator, quantifier, subquery);
    }

    @Override
    public String render() {
        return String.format("%s %s %s %s", left.render(), operator.getSymbol(), quantifier.getKeyword(), subquery.render());
    }

    @Override
    public QualifiedComparison self() {
        return this;
    }
}
