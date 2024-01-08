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

public class DerbyComplexQuery extends SqlExpression<DerbyComplexQuery> {
    private final StringBuilder queryBuilder = new StringBuilder();

    private DerbyComplexQuery(String starting) {
        queryBuilder.append(starting);
    }

    public static DerbyComplexQuery startsWithUnion(DerbyUnion union) {
        return new DerbyComplexQuery(String.format("(%s)", union.render()));
    }

    public static DerbyComplexQuery startsWithIntersection(DerbyIntersect intersect) {
        return new DerbyComplexQuery(String.format("(%s)", intersect.render()));
    }

    public static DerbyComplexQuery startsWithExcept(DerbyExcept except) {
        return new DerbyComplexQuery(String.format("(%s)", except.render()));
    }

    public static DerbyComplexQuery startsWithQuery(Query query) {
        return new DerbyComplexQuery(query.render());
    }

    public static DerbyComplexQuery startsWithValues(Values values) {
        return new DerbyComplexQuery(values.render());
    }

    public DerbyComplexQuery unionWithUnion(DerbyUnion union) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nUNION\n(\n\t%s\n)", queryBuilder, union.render()));
    }

    public DerbyComplexQuery unionWithIntersect(DerbyIntersect intersect) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nUNION\n(\n\t%s\n)", queryBuilder, intersect.render()));
    }

    public DerbyComplexQuery unionWithExcept(DerbyExcept except) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nUNION\n(\n\t%s\n)", queryBuilder, except.render()));
    }

    public DerbyComplexQuery unionWithQuery(Query query) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nUNION\n%s", queryBuilder, query.render()));
    }

    public DerbyComplexQuery unionWithValues(Values values) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nUNION\n%s", queryBuilder, values.render()));
    }

    public DerbyComplexQuery intersectWithUnion(DerbyUnion union) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nINTERSECT\n(\n\t%s\n)", queryBuilder, union.render()));
    }

    public DerbyComplexQuery intersectWithIntersect(DerbyIntersect intersect) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nINTERSECT\n(\n\t%s\n)", queryBuilder, intersect.render()));
    }

    public DerbyComplexQuery intersectWithExcept(DerbyExcept except) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nINTERSECT\n(\n\t%s\n)", queryBuilder, except.render()));
    }

    public DerbyComplexQuery intersectWithQuery(Query query) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nINTERSECT\n%s", queryBuilder, query.render()));
    }

    public DerbyComplexQuery intersectWithValues(Values values) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nINTERSECT\n(%s)", queryBuilder, values.render()));
    }

    public DerbyComplexQuery exceptWithUnion(DerbyUnion union) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nEXCEPT\n(\n\t%s\n)", queryBuilder, union.render()));
    }

    public DerbyComplexQuery exceptWithIntersect(DerbyIntersect intersect) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nEXCEPT\n(\n\t%s\n)", queryBuilder, intersect.render()));
    }

    public DerbyComplexQuery exceptWithExcept(DerbyExcept except) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nEXCEPT\n(\n\t%s\n)", queryBuilder, except.render()));
    }

    public DerbyComplexQuery exceptWithQuery(Query query) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nEXCEPT\n%s", queryBuilder, query.render()));
    }

    public DerbyComplexQuery exceptWithValues(Values values) {
        return new DerbyComplexQuery(String.format("(\n\t%s\n)\nEXCEPT\n%s", queryBuilder, values.render()));
    }

    @Override
    public String render() {
        if (queryBuilder.isEmpty()) {
            throw new IllegalStateException("No query built.");
        }
        return queryBuilder.toString();
    }

    @Override
    public DerbyComplexQuery self() {
        return this;
    }
}

