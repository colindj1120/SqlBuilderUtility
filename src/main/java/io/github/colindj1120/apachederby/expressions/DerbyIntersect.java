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

public class DerbyIntersect extends SqlExpression<DerbyIntersect> {
    private final StringBuilder intersectBuilder = new StringBuilder();

    private DerbyIntersect(String starting) {
        intersectBuilder.append(starting);
    }

    public static DerbyIntersect queryStart(Query query) {
        return new DerbyIntersect(query.render());
    }

    public DerbyIntersect valuesStart(Values values) {
        return new DerbyIntersect(values.render());
    }

    public DerbyIntersect values(Values values) {
        return append(values);
    }

    public DerbyIntersect query(Query query) {
        return append(query);
    }

    public DerbyIntersect intersect() {
        return append("INTERSECT");
    }

    public DerbyIntersect intersectAll() {
        return append("INTERSECT ALL");
    }

    public DerbyIntersect intersectDistinct() {
        return append("INTERSECT DISTINCT");
    }

    private DerbyIntersect append(SqlExpression<?> values) {
        intersectBuilder.append("\n")
                        .append(values.render());
        return this;
    }

    private DerbyIntersect append(String intersectType) {
        intersectBuilder.append("\n")
                        .append(intersectType);
        return this;
    }

    @Override
    public String render() {
        if (intersectBuilder.isEmpty()) {
            throw new IllegalStateException("No queries added to INTERSECT operation.");
        }

        return intersectBuilder.toString();
    }

    @Override
    public DerbyIntersect self() {
        return this;
    }
}
