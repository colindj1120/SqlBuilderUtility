package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

@SuppressWarnings("unused")
public class IsNull extends SqlExpression<IsNull> {
    private final SqlExpression<?> expression;
    private final boolean not;

    private IsNull(SqlExpression<?> expression, boolean not) {
        this.expression = expression;
        this.not = not;
    }

    public static IsNull isNull(SqlExpression<?> expression) {
        return new IsNull(expression, false);
    }

    public static IsNull isNotNull(SqlExpression<?> expression) {
        return new IsNull(expression, true);
    }

    @Override
    public String render() {
        return String.format("%s %s", expression.render(), not ? Operator.IS_NOT_NULL.getSymbol() : Operator.IS_NULL.getSymbol());
    }

    @Override
    public IsNull self() {
        return this;
    }
}
