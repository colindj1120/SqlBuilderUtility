package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;
import com.hyperion.sqlbuilder.datatypes.SqlStandard.Quantifier;

/**
 * Represents a qualified comparison expression with SQL quantifiers.
 */
public class QualifiedComparison extends SqlExpression<QualifiedComparison> {
    private final SqlExpression<?> left;
    private final Operator operator;
    private final Quantifier quantifier;
    private final Subquery subquery;

    private QualifiedComparison(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        this.left = left;
        this.operator = operator;
        this.quantifier = quantifier;
        this.subquery = subquery;
    }

    public static QualifiedComparison create(SqlExpression<?> left, Operator operator, Quantifier quantifier, Subquery subquery) {
        return new QualifiedComparison(left, operator, quantifier, subquery);
    }

    @Override
    public String render() {
        return String.format("%s %s %s %s", left.render(), operator.getSymbol(), quantifier.getKeyword(), subquery.render());
    }

    @Override
    public QualifiedComparison self() {
        return this;
    }
}
