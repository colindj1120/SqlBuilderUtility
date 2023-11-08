package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

public class SetColumn extends SqlExpression<SetColumn> {
    private SetColumn(String column, SqlExpression<?> expression) {
        super();
        this.expression.append(column)
                       .append(" ")
                       .append(Operator.EQUALS)
                       .append(" ")
                       .append(expression.render());
    }

    public static SetColumn set(String column, SqlExpression<?> expression) {
        return new SetColumn(column, expression);
    }

    @Override
    protected SetColumn self() {
        return this;
    }
}
