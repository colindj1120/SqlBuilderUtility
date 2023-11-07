package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;

/**
 * Class representing an SQL expression using operators.
 */
public class BinaryExpression implements SqlExpression {
    private final SqlExpression leftOperand;
    private final Operator      operator;
    private final SqlExpression rightOperand;

    public static BinaryExpression create(SqlExpression leftOperand, Operator operator, SqlExpression rightOperand) {
        return new BinaryExpression(leftOperand, operator, rightOperand);
    }

    private BinaryExpression(SqlExpression leftOperand, Operator operator, SqlExpression rightOperand) {
        this.leftOperand = leftOperand;
        this.operator = operator;
        this.rightOperand = rightOperand;
    }

    @Override
    public String render() {
        return String.format("%s %s %s", leftOperand.render(), operator.getSymbol(), rightOperand.render());
    }
}

