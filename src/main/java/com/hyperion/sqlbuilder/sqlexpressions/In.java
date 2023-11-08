package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class In extends SqlExpression<In> {
    private In(SqlExpression<?> field, SqlExpression<?>[] values, boolean not) {
        super();
        String valueList = Stream.of(values)
                                 .map(SqlExpression::render)
                                 .collect(Collectors.joining(", "));
        this.expression.append(field.render())
                       .append(not ? " NOT IN (" : " IN (")
                       .append(valueList)
                       .append(")");
    }

    public static In createIn(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, false);
    }

    public static In createNotIn(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, true);
    }

    @Override
    protected In self() {
        return this;
    }
}
