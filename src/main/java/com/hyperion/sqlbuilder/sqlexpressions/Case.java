package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Objects;

public class Case extends SqlExpression<Case> {
    private final StringBuilder expression = new StringBuilder();

    private Case(SqlExpression<?> when, SqlExpression<?> then, SqlExpression<?> elseCase) {
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
    public String render() {
        return expression.toString();
    }

    @Override
    public Case self() {
        return this;
    }
}
