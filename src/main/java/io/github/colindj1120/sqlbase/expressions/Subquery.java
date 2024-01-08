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

public class Subquery extends SqlExpression<Subquery> {
    private final String query;
    private final String alias;

    private Subquery(String query, String alias) {
        this.query = query;
        this.alias = alias;
    }

    public static Subquery subquery(String query) {
        return new Subquery(query, null);
    }

    public static Subquery subquery(String query, String alias) {
        return new Subquery(query, alias);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias)
                       .map(alias -> String.format("(%s) AS %s", query, alias))
                       .orElse(String.format("(%s)", query));
    }

    @Override
    public Subquery alias(String alias) {
        return new Subquery(this.query, alias);
    }

    @Override
    public Subquery self() {
        return this;
    }
}

