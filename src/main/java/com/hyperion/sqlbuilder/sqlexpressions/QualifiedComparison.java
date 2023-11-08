package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;
import com.hyperion.sqlbuilder.datatypes.SqlStandard.Quantifier;

/**
 * Represents a qualified comparison expression with SQL quantifiers.
 */
public class QualifiedComparison extends SqlExpression<QualifiedComparison> {
    private QualifiedComparison(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        super();
        this.expression.append(left.render())
                       .append(" ")
                       .append(operator.getSymbol())
                       .append(" ")
                       .append(quantifier.getKeyword())
                       .append(" ")
                       .append(subquery.render());
    }

    public static QualifiedComparison create(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        return new QualifiedComparison(left, operator, quantifier, subquery);
    }

    @Override
    protected QualifiedComparison self() {
        return this;
    }
}
