package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Coalesce extends SqlExpression<Coalesce> {
    private Coalesce(SqlExpression<?>[] expressions) {
        super();

        String expressionString = Arrays.stream(expressions)
                                        .map(SqlExpression::render)
                                        .collect(Collectors.joining(", "));
        this.expression.append(expressionString);
    }

    public static Coalesce create(SqlExpression<?>... expressions) {
        return new Coalesce(expressions);
    }

    @Override
    protected Coalesce self() {
        return this;
    }
}
