package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

public class IsNull extends SqlExpression<IsNull> {
    private IsNull(SqlExpression<?> expression, boolean not) {
        super();
        this.expression.append(expression)
                       .append(not ? Operator.IS_NOT_NULL.getSymbol() : Operator.IS_NULL.getSymbol());
    }

    public static IsNull isNull(SqlExpression<?> expression) {
        return new IsNull(expression, false);
    }

    public static IsNull isNotNull(SqlExpression<?> expression) {
        return new IsNull(expression, true);
    }

    @Override
    protected IsNull self() {
        return this;
    }
}
