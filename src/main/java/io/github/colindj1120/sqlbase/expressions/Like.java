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

/**
 * Represents a SQL LIKE expression with an optional escape character.
 */

public class Like extends SqlExpression<Like> {
    private final SqlExpression<?> field;
    private final String           pattern;
    private final String           escapeChar;
    private final boolean          not;

    private Like(SqlExpression<?> field, String pattern, String escapeChar, boolean not) {
        this.field      = field;
        this.pattern    = pattern;
        this.escapeChar = escapeChar;
        this.not        = not;
    }

    public static Like like(SqlExpression<?> field, String pattern) {
        return new Like(field, pattern, null, false);
    }

    public static Like like(SqlExpression<?> field, String pattern, String escapeChar) {
        return new Like(field, pattern, escapeChar, false);
    }

    public static Like notLike(SqlExpression<?> field, String pattern) {
        return new Like(field, pattern, null, true);
    }

    public static Like notLike(SqlExpression<?> field, String pattern, String escapeChar) {
        return new Like(field, pattern, escapeChar, true);
    }

    @Override
    public String render() {
        String escape = Optional.ofNullable(escapeChar)
                                .map(str -> String.format(" ESCAPE '%s'", escapeChar))
                                .orElse("");
        return String.format("%s %s '%s'%s", field.render(), not ? "NOT LIKE" : "LIKE", pattern, escape);
    }

    @Override
    public Like self() {
        return this;
    }
}
