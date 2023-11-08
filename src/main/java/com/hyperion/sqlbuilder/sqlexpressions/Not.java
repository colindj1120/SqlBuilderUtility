package com.hyperion.sqlbuilder.sqlexpressions;

public class Not extends SqlExpression<Not> {
    private Not(SqlExpression<?> expression) {
        super();
        this.expression.append("NOT ")
                       .append(expression.render());
    }

    public static Not create(SqlExpression<?> expression) {
        return new Not(expression);
    }

    @Override
    protected Not self() {
        return this;
    }
}
