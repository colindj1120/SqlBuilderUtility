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
package io.github.colindj1120.apachederby.expressions;

import io.github.colindj1120.sqlbase.expressions.SqlExpression;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConcat extends SqlExpression<DerbyConcat> {
    private final String concatenatedParts;

    private DerbyConcat(String[] parts) {
        this.concatenatedParts = Arrays.stream(parts)
                                       .map(part -> "'" + part.replace("'", "''") + "'") // Handle single quotes in SQL
                                       .collect(Collectors.joining(" || "));
    }

    public static DerbyConcat derbyConcat(String... parts) {
        return new DerbyConcat(parts);
    }

    @Override
    public String render() {
        return concatenatedParts;
    }

    @Override
    public DerbyConcat self() {
        return this;
    }
}
