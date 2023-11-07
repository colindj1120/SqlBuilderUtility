package com.hyperion.sqlbuilder.sqlexpressions;

public class UpdateExpression implements SqlExpression{
    private final String        column;
    private final SqlExpression expression;

    public static UpdateExpression create(String column, SqlExpression expression) {
        return new UpdateExpression(column, expression);
    }

    private UpdateExpression(String column, SqlExpression expression) {
        this.column = column;
        this.expression = expression;
    }

    @Override
    public String render() {
        return String.format("%s = %s", column, expression.render());
    }
}
