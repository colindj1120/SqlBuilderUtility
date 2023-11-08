package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

/**
 * Class representing an SQL expression using operators.
 */
public class Comparison extends SqlExpression<Comparison> {
    private Comparison(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        super();
        this.expression.append(leftOperand.render())
                       .append(" ")
                       .append(operator.getSymbol())
                       .append(" ")
                       .append(rightOperand.render());
    }

    public static Comparison create(SqlExpression<?> leftOperand, Operator operator, SqlExpression<?> rightOperand) {
        return new Comparison(leftOperand, operator, rightOperand);
    }

    @Override
    protected Comparison self() {
        return this;
    }
}

