package com.hyperion.sqlbuilder.builders;

import com.hyperion.sqlbuilder.datatypes.Join.JoinType;
import com.hyperion.sqlbuilder.sqlexpressions.*;

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

    /**
     * Appends a FROM clause to the SQL query.
     *
     * @param table
     *         the table name to select from
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public T from(Table table) {
        builder.append("\nFROM ")
               .append(table.render());
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

    public T defaultValues() {
        builder.append(" DEFAULT VALUES");
        return self();
    }

    public T values(SqlExpression<?>... expressions) {
        builder.append("\nVALUES ");
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(SqlExpression::render)
                                                         .collect(Collectors.joining(", ")));
        builder.append(valueString);
        return self();
    }

    public T groupedValues(SqlExpression<?>[]... expressions) {
        builder.append("\nVALUES ");
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(expression -> String.format("(%s)", Arrays.stream(expression)
                                                                                                        .map(SqlExpression::render)
                                                                                                        .collect(Collectors.joining(", "))))
                                                         .collect(Collectors.joining(", ")));
        builder.append(valueString);
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
