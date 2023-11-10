package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Values extends SqlExpression<Values> {
    private final String valueString;

    private Values(String valueString) {
        this.valueString = valueString;
    }


    public static Values values(SqlExpression<?>... expressions) {
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(SqlExpression::render)
                                                         .collect(Collectors.joining(", ")));
        return new Values(valueString);
    }

    public static Values groupedValues(SqlExpression<?>[]... expressions) {
        String valueString = String.format("(%s)", Arrays.stream(expressions)
                                                         .map(expression -> String.format("(%s)", Arrays.stream(expression)
                                                                                                        .map(SqlExpression::render)
                                                                                                        .collect(Collectors.joining(", "))))
                                                         .collect(Collectors.joining(", ")));
        return new Values(valueString);
    }

    @Override
    public String render() {
        return String.format("VALUES %s", valueString);
    }

    @Override
    public Values self() {
        return this;
    }
}
