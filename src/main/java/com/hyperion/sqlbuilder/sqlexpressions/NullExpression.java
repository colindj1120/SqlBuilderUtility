package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

public class NullExpression implements SqlExpression{
    private final SqlExpression expression;
    private final boolean not;

    public static NullExpression createNull(SqlExpression expression) {
        return new NullExpression(expression, false);
    }

    public static NullExpression createNotNull(SqlExpression expression) {
        return new NullExpression(expression, true);
    }

    private NullExpression(SqlExpression expression, boolean not) {
        this.expression = expression;
        this.not = not;
    }

    @Override
    public String render() {
        return String.format("%s %s", expression.render(), not ? Operator.IS_NOT_NULL : Operator.IS_NULL );
    }
}
