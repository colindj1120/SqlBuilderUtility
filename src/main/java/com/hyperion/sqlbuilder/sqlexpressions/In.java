package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class In extends SqlExpression<In> {
    private final SqlExpression<?> field;
    private final SqlExpression<?>[] values;
    private final boolean not;

    private In(SqlExpression<?> field, SqlExpression<?>[] values, boolean not) {
        this.field = field;
        this.values = values;
        this.not = not;
    }

    public static In in(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, false);
    }

    public static In notIn(SqlExpression<?> field, SqlExpression<?>... values) {
        return new In(field, values, true);
    }

    @Override
    public String render() {
        String valueList = Stream.of(values)
                                 .map(SqlExpression::render)
                                 .collect(Collectors.joining(", "));
        return String.format("%s %s (%s)", field.render(), not ? "NOT IN" : "IN", valueList);
    }

    @Override
    public In self() {
        return this;
    }
}
