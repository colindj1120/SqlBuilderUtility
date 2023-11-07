package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CoalesceExpression implements SqlExpression {
    private String coalescedExpressions;

    private CoalesceExpression(SqlExpression[] expressions) {
        this.coalescedExpressions = Arrays.stream(expressions)
                                          .map(SqlExpression::render)
                                          .collect(Collectors.joining(", "));
    }

    public static CoalesceExpression create(SqlExpression... expressions) {
        return new CoalesceExpression(expressions);
    }

    @Override
    public String render() {
        return coalescedExpressions;
    }
}
