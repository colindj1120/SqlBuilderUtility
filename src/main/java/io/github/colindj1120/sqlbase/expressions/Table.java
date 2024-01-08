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

import java.util.Optional;

public class Table extends SqlExpression<Table> {
    private final   String tableName;
    protected final String correlation;
    private final   String alias;

    protected Table(String tableName, String correlation, String alias) {
        this.tableName   = tableName;
        this.correlation = correlation;
        this.alias       = alias;
    }

    public static Table name(String tableName) {
        return new Table(tableName, null, null);
    }

    public static Table nameWithCorrelation(String tableName, String correlation) {
        return new Table(tableName, null, correlation);
    }

    public static Table nameWithAlias(String tableName, String alias) {
        return new Table(tableName, alias, null);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias)
                       .map(alias -> String.format("%s AS %s", tableName, alias))
                       .orElse(String.format("%s%s", tableName, Optional.ofNullable(correlation)
                                                                        .map(str -> String.format(" %s", str))
                                                                        .orElse("")));
    }

    @Override
    public Table correlation(String correlation) {
        return new Table(this.tableName, correlation, null);
    }

    @Override
    public Table alias(String alias) {
        return new Table(this.tableName, null, alias);
    }

    @Override
    public Table self() {
        return this;
    }

    public Table nameOnly() {
        return new Table(this.tableName, null, null);
    }
}
