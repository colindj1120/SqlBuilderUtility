package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Objects;

public class Case extends SqlExpression<Case> {
    private Case(SqlExpression<?> when, SqlExpression<?> then, SqlExpression<?> elseCase) {
        super();

        if (Objects.isNull(when) || Objects.isNull(then)) {
            this.expression.append("ELSE ")
                           .append(elseCase.render());
        } else {
            this.expression.append("WHEN ")
                           .append(when.render())
                           .append(" THEN ")
                           .append(then.render());
        }
    }

    public static Case createWhenThenPair(SqlExpression<?> when, SqlExpression<?> then) {
        return new Case(when, then, null);
    }

    public static Case createElseCase(SqlExpression<?> elseCase) {
        return new Case(null, null, elseCase);
    }

    @Override
    protected Case self() {
        return this;
    }
}
