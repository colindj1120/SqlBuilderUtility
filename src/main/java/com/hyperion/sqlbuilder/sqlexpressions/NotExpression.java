package com.hyperion.sqlbuilder.sqlexpressions;

public class NotExpression implements SqlExpression {
    private final SqlExpression expression;

    public static NotExpression create(SqlExpression expression) {
        return new NotExpression(expression);
    }

    private NotExpression(SqlExpression expression) {
        this.expression = expression;
    }

    @Override
    public String render() {
        return String.format("NOT %s", expression.render());
    }
}
