package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InExpression implements SqlExpression {
    private final SqlExpression field;
    private final SqlExpression[] values;
    private final boolean not;

    public static InExpression createIn(SqlExpression field, SqlExpression... values) {
        return new InExpression(field, values, false);
    }

    public static InExpression createNotIn(SqlExpression field, SqlExpression... values) {
        return new InExpression(field, values, true);
    }

    private InExpression(SqlExpression field, SqlExpression[] values, boolean not) {
        this.field = field;
        this.values = values;
        this.not = not;
    }

    @Override
    public String render() {
        String valueList = Stream.of(values)
                                 .map(SqlExpression::render)
                                 .collect(Collectors.joining(", "));
        return String.format("%s %sIN (%s)", field.render(), not ? "NOT " : "", valueList);
    }
}
