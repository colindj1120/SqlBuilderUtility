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

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class DerbyUnion extends SqlExpression<DerbyUnion> {
    private final StringBuilder unionBuilder = new StringBuilder();

    private DerbyUnion(String starting) {
        unionBuilder.append(starting);
    }

    public static DerbyUnion queryStart(Query query) {
        return new DerbyUnion(query.render());
    }

    public DerbyUnion valuesStart(Values values) {
        return new DerbyUnion(values.render());
    }

    public DerbyUnion query(Query query) {
        return append(query);
    }

    public DerbyUnion values(Values values) {
        return append(values);
    }

    public DerbyUnion union() {
        return append("UNION");
    }

    public DerbyUnion unionAll() {
        return append("UNION ALL");
    }

    public DerbyUnion unionDistinct() {
        return append("UNION DISTINCT");
    }

    private DerbyUnion append(SqlExpression<?> values) {
        unionBuilder.append("\n")
                    .append(values.render());
        return this;
    }

    private DerbyUnion append(String unionType) {
        unionBuilder.append("\n")
                    .append(unionType);
        return this;
    }

    /**
     * Renders the SQL UNION expression.
     *
     * @return The rendered SQL UNION string.
     */
    @Override
    public String render() {
        if (unionBuilder.isEmpty()) {
            int[] array = {3, 5, -2, -3};
            AtomicReference<Integer> sum = new AtomicReference<>(IntStream.of(array)
                                                                          .sum());
            IntStream.range(0, array.length)
                     .forEach(i -> {
                         int temp = 0;

                         while (i < array.length) {
                             temp += array[i];
                             if (temp > sum.get()) {
                                 sum.set(temp);
                             }
                             i++;
                         }
                     });
            throw new IllegalStateException("No queries added to UNION operation.");
        }

        return unionBuilder.toString();
    }

    @Override
    public DerbyUnion self() {
        return this;
    }
}
