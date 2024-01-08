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
package io.github.colindj1120.sqlbase.builder;

import io.github.colindj1120.sqlbase.expressions.*;
import io.github.colindj1120.sqlbase.interfaces.JoinType;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract class representing a SQL builder that uses functional programming concepts to construct SQL queries in a type-safe manner. This class leverages generics to ensure that only the correct
 * subclass instance is returned during the build process.
 *
 * @param <T>
 *         the type of the concrete subclass, used for fluent method chaining
 *
 * @author Colin Jokisch
 * @version 1.0
 */
@SuppressWarnings({"UnusedReturnValue", "unused"})
public abstract class SqlBuilder<T extends SqlBuilder<T>> {
    protected StringBuilder builder;

    /**
     * Initializes the SQL builder with an empty StringBuilder.
     */
    public SqlBuilder() {
        this.builder = new StringBuilder();
    }

    //TODO: CREATE TABLE "" AS (subquery) WITH NO DATA (derby specific maybe)
    public T createTable(Table table) {
        builder.append("CREATE TABLE ")
               .append(table.nameOnly()
                            .render());
        return self();
    }

    /**
     * Method used internally to cast the current instance to the type T.
     *
     * @return the current instance cast to type T
     */
    protected abstract T self();

    public T endTable() {
        builder.append("\n)");
        return self();
    }

    // Method to select all columns
    public T selectAll() {
        return select(Column.all());
    }

    public T select(SqlExpression<?>... expressions) {
        builder.append("SELECT ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(",\n       "));
        builder.append(renderedExpressions);
        return self();
    }

    // Method to select distinct columns or expressions
    public T selectDistinct(SqlExpression<?>... expressions) {
        builder.append("SELECT DISTINCT ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(",\n                "));
        builder.append(renderedExpressions);
        return self();
    }

    public T from(SqlExpression<?> expression) {
        builder.append("\nFROM ")
               .append(expression.render());
        return self();
    }

    public T join(JoinType joinType, SqlExpression<?> tableExpression) {
        builder.append("\n")
               .append(joinType.getJoinTypeName())
               .append(" ")
               .append(tableExpression.render());
        return self();
    }

    public T on(SqlExpression<?> joinCondition) {
        if (joinCondition != null) {
            builder.append(" ON ")
                   .append(joinCondition.render());
        }
        return self();
    }

    public T using(Column... columns) {
        if (columns != null && columns.length > 0) {
            builder.append(" USING (")
                   .append(Arrays.stream(columns)
                                 .map(Column::render)
                                 .collect(Collectors.joining(", ")))
                   .append(")");
        }
        return self();
    }

    public T where(SqlExpression<?> expression) {
        builder.append("\nWHERE ")
               .append(expression.render());
        return self();
    }

    // Method for ORDER BY clause
    public T orderBy(OrderByExpression... expressions) {
        builder.append("\nORDER BY ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(OrderByExpression::render)
                                           .collect(Collectors.joining(", "));
        builder.append(renderedExpressions);
        return self();
    }

    // Method for GROUP BY clause
    public T groupBy(Column... columns) {
        builder.append("\nGROUP BY ")
               .append(Arrays.stream(columns)
                             .map(Column::render)
                             .collect(Collectors.joining(", ")));
        return self();
    }

    // Method for GROUP BY with ROLLUP (if supported by the DBMS)
    public T groupByRollup(Column... columns) {
        builder.append("\nGROUP BY ROLLUP (")
               .append(Arrays.stream(columns)
                             .map(Column::render)
                             .collect(Collectors.joining(", ")))
               .append(")");
        return self();
    }

    // Method for HAVING clause
    public T having(SqlExpression<?> condition) {
        builder.append("\nHAVING ")
               .append(condition.render());
        return self();
    }

    public T offset(int offset) {
        builder.append("\nOFFSET ")
               .append(offset)
               .append(offset == 1 ? " ROW" : " ROWS");
        return self();
    }

    public T fetchFirst(int rowCount) {
        builder.append("\nFETCH FIRST ")
               .append(rowCount)
               .append(rowCount == 1 ? " ROW" : " ROWS")
               .append(" ONLY");
        return self();
    }

    public T fetchNext(int rowCount) {
        builder.append("\nFETCH NEXT ")
               .append(rowCount)
               .append(rowCount == 1 ? " ROW" : " ROWS")
               .append(" ONLY");
        return self();
    }

    // Method to construct a DELETE statement
    public T deleteFrom(Table table) {
        builder.append("DELETE FROM ")
               .append(table.render());
        return self();
    }

    // Method to construct an UPDATE statement
    public T update(Table table, SetColumn... expressions) {
        builder.append("UPDATE ")
               .append(table.render());
        builder.append("\nSET ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(",\n    "));
        builder.append(renderedExpressions);
        return self();
    }

    public T insertInto(Table tableName, Column... columns) {
        builder.append("INSERT INTO ")
               .append(tableName.nameOnly()
                                .render());
        if (columns.length > 0) {
            builder.append(" (")
                   .append(Arrays.stream(columns)
                                 .map(Column::nameOnly)
                                 .map(Column::render)
                                 .collect(Collectors.joining(", ")))
                   .append(")");
        }
        return self();
    }

    public T values(Values values) {
        builder.append("\n")
               .append(values.render());
        return self();
    }

    /**
     * Builds the final SQL query string.
     *
     * @return the constructed SQL query as a String
     */
    public String build() {
        return builder.toString();
    }
}
