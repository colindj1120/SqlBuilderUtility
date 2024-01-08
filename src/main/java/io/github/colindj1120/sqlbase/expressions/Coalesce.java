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

public class Coalesce extends SqlExpression<Coalesce> {
    private final StringBuilder expression = new StringBuilder();

    private Coalesce(SqlExpression<?>[] expressions) {
        String expressionString = Arrays.stream(expressions)
                                        .map(SqlExpression::render)
                                        .collect(Collectors.joining(", "));
        this.expression.append(expressionString);
    }

    public static Coalesce coalesce(SqlExpression<?>... expressions) {
        return new Coalesce(expressions);
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public Coalesce self() {
        return this;
    }
}
