package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Coalesce extends SqlExpression<Coalesce> {
    private final StringBuilder expression = new StringBuilder();

    private Coalesce(SqlExpression<?>[] expressions) {
        String expressionString = Arrays.stream(expressions)
                                        .map(SqlExpression::render)
                                        .collect(Collectors.joining(", "));
        this.expression.append(expressionString);
    }

    public static Coalesce coalesce(SqlExpression<?>... expressions) {
        return new Coalesce(expressions);
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public Coalesce self() {
        return this;
    }
}
