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

import io.github.colindj1120.sqlbase.interfaces.SqlFunctionProvider;

import java.util.Optional;

public class SqlFunction extends SqlExpression<SqlFunction> {
    private final String function;
    private final String alias;

    private SqlFunction(SqlFunctionProvider functionProvider, String alias, SqlExpression<?>... args) {
        this.function = functionProvider.provideFunction(args);
        this.alias    = alias;
    }

    private SqlFunction(String function, String alias) {
        this.function = function;
        this.alias    = alias;
    }

    public static SqlFunction function(SqlFunctionProvider functionProvider, SqlExpression<?>... args) {
        return new SqlFunction(functionProvider, null, args);
    }

    public static SqlFunction function(SqlFunctionProvider functionProvider, String alias, SqlExpression<?>... args) {
        return new SqlFunction(functionProvider, alias, args);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias)
                       .map(alias -> String.format("%s AS %s", function, alias))
                       .orElse(function);
    }

    @Override
    public SqlFunction alias(String alias) {
        return new SqlFunction(this.function, alias);
    }

    @Override
    public SqlFunction self() {
        return this;
    }
}


