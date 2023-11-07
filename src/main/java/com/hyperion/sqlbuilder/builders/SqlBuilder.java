package com.hyperion.sqlbuilder.builders;

import com.hyperion.sqlbuilder.datatypes.JoinDefinition;
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
@SuppressWarnings("UnusedReturnValue")
public abstract class SqlBuilder<T extends SqlBuilder<T>> {
    protected StringBuilder builder;

    /**
     * Initializes the SQL builder with an empty StringBuilder.
     */
    public SqlBuilder() {
        this.builder = new StringBuilder();
    }

    //TODO: CREATE TABLE "" AS (subquery) WITH NO DATA (derby specific maybe)
    public T createTable(String tableName) {
        builder.append("CREATE TABLE ")
               .append(tableName);
        return self();
    }

    public T endTable() {
        builder.append("\n)");
        return self();
    }

    // Method to select all columns
    public T selectAll() {
        return select(SqlExpression.allColumns());
    }

    // Method to select distinct columns or expressions
    public T selectDistinct(SqlExpression... expressions) {
        return select(SqlExpression.distinct(expressions));
    }

    public T select(SqlExpression... expressions) {
        builder.append("SELECT ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(", "));
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
    public T from(String table) {
        builder.append(" FROM ")
               .append(table);
        return self();
    }

    public T join(JoinDefinition joinDef, SqlExpression tableExpression) {
        builder.append(" ")
               .append(joinDef.getSql())
               .append(" ")
               .append(tableExpression.render());
        return self();
    }

    public T on(SqlExpression joinCondition) {
        if (joinCondition != null) {
            builder.append(" ON ")
                   .append(joinCondition.render());
        }
        return self();
    }

    public T using(String... columns) {
        if (columns != null && columns.length > 0) {
            builder.append(" USING (")
                   .append(String.join(", ", columns))
                   .append(")");
        }
        return self();
    }

    public T where(SqlExpression expression) {
        builder.append(" WHERE ")
               .append(expression.render());
        return self();
    }

    // Method for ORDER BY clause
    public T orderBy(OrderByExpression... expressions) {
        builder.append(" ORDER BY ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(OrderByExpression::render)
                                           .collect(Collectors.joining(", "));
        builder.append(renderedExpressions);
        return self();
    }

    // Method for GROUP BY clause
    public T groupBy(String... columns) {
        builder.append(" GROUP BY ")
               .append(String.join(", ", columns));
        return self();
    }

    // Method for GROUP BY with ROLLUP (if supported by the DBMS)
    public T groupByRollup(String... columns) {
        builder.append(" GROUP BY ROLLUP (")
               .append(String.join(", ", columns))
               .append(")");
        return self();
    }

    // Method for HAVING clause
    public T having(SqlExpression condition) {
        builder.append(" HAVING ")
               .append(condition.render());
        return self();
    }

    public T offset(int offset) {
        builder.append(" OFFSET ")
               .append(offset)
               .append(offset == 1 ? " ROW" : " ROWS");
        return self();
    }

    public T fetchFirst(int rowCount) {
        builder.append(" FETCH FIRST ")
               .append(rowCount)
               .append(rowCount == 1 ? " ROW" : " ROWS")
               .append(" ONLY");
        return self();
    }

    public T fetchNext(int rowCount) {
        builder.append(" FETCH NEXT ")
               .append(rowCount)
               .append(rowCount == 1 ? " ROW" : " ROWS")
               .append(" ONLY");
        return self();
    }

    // Method to construct a DELETE statement
    public T deleteFrom(ColumnExpression table) {
        builder.append("DELETE FROM ")
               .append(table);
        return self();
    }

    // Method to construct an UPDATE statement
    public T update(ColumnExpression table) {
        builder.append("UPDATE ")
               .append(table.render());
        return self();
    }

    // Method to set values for an UPDATE statement
    public T set(UpdateExpression... expressions) {
        builder.append(" SET ");
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(", "));
        builder.append(renderedExpressions);
        return self();
    }

    public T insertInto(String tableName, String... columns) {
        builder.append("INSERT INTO ")
               .append(tableName);
        if (columns.length > 0) {
            builder.append(" (")
                   .append(String.join(", ", columns))
                   .append(")");
        }
        return self();
    }

    public T defaultValues() {
        builder.append(" DEFAULT VALUES");
        return self();
    }

    public T subqueryValue(SubqueryExpression expression) {
        builder.append(" VALUES ")
               .append(expression.render());
        return self();
    }

    public T values(ConstantExpression... expressions) {
        builder.append(" VALUES ");
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(ConstantExpression::render)
                                                         .collect(Collectors.joining(", ")));
        builder.append(valueString);
        return self();
    }

    public T groupedValues(ConstantExpression[]... expressions) {
        builder.append(" VALUES ");
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(expression -> String.format("(%s)", Arrays.stream(expression)
                                                                                                        .map(SqlExpression::render)
                                                                                                        .collect(Collectors.joining(", "))))
                                                         .collect(Collectors.joining(", ")));
        builder.append(valueString);
        return self();
    }

    /**
     * Method used internally to cast the current instance to the type T.
     *
     * @return the current instance cast to type T
     */
    protected abstract T self();

    /**
     * Builds the final SQL query string.
     *
     * @return the constructed SQL query as a String
     */
    public String build() {
        return builder.toString();
    }
}
