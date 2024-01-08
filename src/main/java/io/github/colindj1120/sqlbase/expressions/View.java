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

public class View extends SqlExpression<View> {
    private final String schemaName;
    private final String sql92Identifier;

    public View(String schemaName, String sql92Identifier) {
        this.schemaName      = schemaName;
        this.sql92Identifier = sql92Identifier;
    }

    public static View view(String sql92Identifier) {
        return new View(null, sql92Identifier);
    }

    public static View view(String schemaName, String sql92Identifier) {
        return new View(schemaName, sql92Identifier);
    }

    @Override
    public String render() {
        return Optional.ofNullable(schemaName)
                       .map(str -> String.format("%s.%s", str, sql92Identifier))
                       .orElse(sql92Identifier);
    }

    @Override
    public View self() {
        return this;
    }
}
