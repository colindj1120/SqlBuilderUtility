package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

@SuppressWarnings("unused")
public class SetColumn extends SqlExpression<SetColumn> {
    private final Column column;
    private final SqlExpression<?> expression;

    private SetColumn(Column column, SqlExpression<?> expression) {
        this.column = column;
        this.expression = expression;
    }

    public static SetColumn set(Column column, SqlExpression<?> expression) {
        return new SetColumn(column, expression);
    }

    @Override
    public String render() {
        return String.format("%s %s %s", column.render(), Operator.EQUALS, expression.render());
    }

    @Override
    public SetColumn self() {
        return this;
    }
}
