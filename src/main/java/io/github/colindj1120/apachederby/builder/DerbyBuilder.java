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
package io.github.colindj1120.apachederby.builder;

import io.github.colindj1120.sqlbase.builder.SqlBuilder;
import io.github.colindj1120.apachederby.DerbyConstraint;
import io.github.colindj1120.apachederby.DerbyDataType;
import io.github.colindj1120.apachederby.expressions.DerbyUnion;
import io.github.colindj1120.sqlbase.expressions.Column;
import io.github.colindj1120.sqlbase.expressions.NextValueFor;
import io.github.colindj1120.sqlbase.expressions.Query;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Concrete implementation of SqlBuilder for Apache Derby. This class extends the generic SqlBuilder to provide Derby-specific SQL building capabilities.
 *
 * @author Colin Jokisch
 * @version 1.0
 */

public class DerbyBuilder extends SqlBuilder<DerbyBuilder> {

    /**
     * Constructs a new DerbyBuilder instance.
     */
    public DerbyBuilder() {
        super();
    }

    /**
     * Method used internally to cast the current instance to the DerbyBuilder type.
     *
     * @return the current instance cast to DerbyBuilder
     */
    @Override
    protected DerbyBuilder self() {
        return this;
    }

    public DerbyBuilder createColumn(Column column, DerbyDataType type, DerbyConstraint... constraints) {
        if (builder.lastIndexOf("(\n") != builder.length() - 2 && builder.lastIndexOf("(\n") != -1) {
            builder.append(",\n");
        } else {
            builder.append(" (\n");
        }
        builder.append("\t")
               .append(column.nameOnly())
               .append(" ")
               .append(type.toString());

        Arrays.stream(constraints)
              .forEach(constraint -> builder.append(" ")
                                            .append(constraint.toString()));
        return this;
    }

    public DerbyBuilder addConstraint(DerbyConstraint constraint) {
        builder.append(",\n\t")
               .append(constraint.toString());
        return this;
    }

    public DerbyBuilder nextValueFor(NextValueFor nextValueFor) {
        builder.append(nextValueFor.render());
        return this;
    }

    /**
     * Appends a FOR UPDATE clause to the SQL query, optionally specifying columns.
     *
     * @param columns
     *         the columns to be updated, varargs to allow specifying multiple columns
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forUpdate(Column... columns) {
        builder.append(" FOR UPDATE");
        if (columns.length > 0) {
            builder.append(" OF ")
                   .append(Arrays.stream(columns)
                                 .map(Column::render)
                                 .collect(Collectors.joining(", ")));
        }
        return this;
    }

    /**
     * Appends a FOR READ ONLY clause to the SQL query.
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forReadOnly() {
        builder.append(" FOR READ ONLY");
        return this;
    }

    /**
     * Appends a FOR FETCH ONLY clause to the SQL query.
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forFetchOnly() {
        builder.append(" FOR FETCH ONLY");
        return this;
    }

    public DerbyBuilder whereCurrentOf(String cursorName) {
        builder.append("\nWHERE CURRENT OF ")
               .append(cursorName);
        return this;
    }

    public DerbyBuilder union(DerbyBuilder query1, DerbyBuilder query2) {
        builder.append(DerbyUnion.queryStart(Query.query(query1.build()))
                                 .union()
                                 .query(Query.query(query2.build()))
                                 .render());
        return this;
    }
}

