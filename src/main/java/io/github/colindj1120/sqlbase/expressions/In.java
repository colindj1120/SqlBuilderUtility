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

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class In extends SqlExpression<In> {
    private final SqlExpression<?>   field;
    private final SqlExpression<?>[] values;
    private final boolean            not;

    private In(SqlExpression<?> field, SqlExpression<?>[] values, boolean not) {
        this.field  = field;
        this.values = values;
        this.not    = not;
    }

    public static In in(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, false);
    }

    public static In notIn(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, true);
    }

    @Override
    public String render() {
        String valueList = Stream.of(values)
                                 .map(SqlExpression::render)
                                 .collect(Collectors.joining(", "));
        return String.format("%s %s (%s)", field.render(), not ? "NOT IN" : "IN", valueList);
    }

    @Override
    public In self() {
        return this;
    }
}
