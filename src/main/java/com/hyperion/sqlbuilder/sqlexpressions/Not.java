package com.hyperion.sqlbuilder.sqlexpressions;

public class Not extends SqlExpression<Not> {
    private final SqlExpression<?> expression;

    private Not(SqlExpression<?> expression) {
        this.expression = expression;
    }

    public static Not create(SqlExpression<?> expression) {
        return new Not(expression);
    }

    @Override
    public String render() {
        return String.format("NOT %s", expression.render());
    }


    @Override
    public Not self() {
        return this;
    }
}
