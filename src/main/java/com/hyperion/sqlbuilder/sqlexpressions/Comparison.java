package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

/**
 * Class representing an SQL expression using operators.
 */
@SuppressWarnings("unused")
public class Comparison extends SqlExpression<Comparison> {
    private final SqlExpression<?> leftOperand;
    private final Operator operator;
    private final SqlExpression<?> rightOperand;

    private Comparison(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    public static Comparison comparison(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        return new Comparison(leftOperand, operator, rightOperand);
    }

    @Override
    public String render() {
        return String.format("%s %s %s", leftOperand.render(), operator.getSymbol(), rightOperand.render());
    }

    @Override
    public Comparison self() {
        return this;
    }
}

