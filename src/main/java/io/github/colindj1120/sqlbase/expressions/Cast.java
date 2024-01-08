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

import io.github.colindj1120.apachederby.DerbyDataType;

import java.util.Optional;

public class Cast extends SqlExpression<Cast> {
    private final StringBuilder expression = new StringBuilder();

    private Cast(String castObj, DerbyDataType castType) {
        this.expression.append(Optional.ofNullable(castObj)
                                       .orElse("NULL"))
                       .append(" AS ")
                       .append(castType.toString());
    }

    public static Cast nullCast(DerbyDataType castType) {
        return new Cast(null, castType);
    }

    public static Cast cast(SqlExpression<?> expression, DerbyDataType castType) {
        return new Cast(expression.render(), castType);
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public Cast self() {
        return null;
    }
}
