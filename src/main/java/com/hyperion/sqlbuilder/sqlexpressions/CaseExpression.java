package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

public class CaseExpression implements SqlExpression {
    private final SqlExpression when;
    private final SqlExpression then;
    private final SqlExpression elseCase;

    private CaseExpression(SqlExpression when, SqlExpression then, SqlExpression elseCase) {
        this.when     = when;
        this.then     = then;
        this.elseCase = elseCase;
    }

    public static CaseExpression createWhenThenPair(SqlExpression when, SqlExpression then) {
        return new CaseExpression(when, then, null);
    }

    public static CaseExpression createElseCase(SqlExpression elseCase) {
        return new CaseExpression(null, null, elseCase);
    }

    @Override
    public String render() {
        return Optional.ofNullable(when)
                       .map(when -> String.format("WHEN %s THEN %s", when.render(), then.render()))
                       .orElse(String.format("ELSE %s", elseCase.render()));
    }
}
