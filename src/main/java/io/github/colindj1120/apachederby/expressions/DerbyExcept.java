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

import io.github.colindj1120.sqlbase.expressions.Query;
import io.github.colindj1120.sqlbase.expressions.SqlExpression;
import io.github.colindj1120.sqlbase.expressions.Values;

public class DerbyExcept extends SqlExpression<DerbyExcept> {
    private final StringBuilder exceptBuilder = new StringBuilder();

    private DerbyExcept(String starting) {
        exceptBuilder.append(starting);
    }

    public static DerbyExcept queryStart(Query query) {
        return new DerbyExcept(query.render());
    }

    public DerbyExcept valuesStart(Values values) {
        return new DerbyExcept(values.render());
    }

    public DerbyExcept values(Values values) {
        return append(values);
    }

    public DerbyExcept query(Query query) {
        return append(query);
    }

    public DerbyExcept except() {
        return append("EXCEPT");
    }

    public DerbyExcept exceptAll() {
        return append("EXCEPT ALL");
    }

    public DerbyExcept exceptDistinct() {
        return append("EXCEPT DISTINCT");
    }

    private DerbyExcept append(SqlExpression<?> values) {
        exceptBuilder.append("\n")
                     .append(values.render());
        return this;
    }

    private DerbyExcept append(String exceptType) {
        exceptBuilder.append("\n")
                     .append(exceptType);
        return this;
    }

    @Override
    public String render() {
        if (exceptBuilder.isEmpty()) {
            throw new IllegalStateException("No queries added to EXCEPT operation.");
        }

        return exceptBuilder.toString();
    }

    @Override
    public DerbyExcept self() {
        return this;
    }
}
