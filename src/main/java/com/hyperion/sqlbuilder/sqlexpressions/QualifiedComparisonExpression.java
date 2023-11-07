package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.Operator;
import com.hyperion.sqlbuilder.datatypes.SqlStandard.Quantifier;

/**
 * Represents a qualified comparison expression with SQL quantifiers.
 */
public class QualifiedComparisonExpression implements SqlExpression {
    private final SqlExpression left;
    private final Operator      operator;
    private final Quantifier    quantifier;
    private final SqlExpression subquery;

    public static QualifiedComparisonExpression create(SqlExpression left, Operator operator, Quantifier quantifier, SubqueryExpression subquery) {
        return new QualifiedComparisonExpression(left, operator, quantifier, subquery);
    }

    private QualifiedComparisonExpression(SqlExpression left, Operator operator, Quantifier quantifier, SubqueryExpression subquery) {
        this.left = left;
        this.operator = operator;
        this.quantifier = quantifier;
        this.subquery = subquery;
    }

    @Override
    public String render() {
        return String.format("%s %s %s %s", left.render(), operator.getSymbol(), quantifier.getKeyword(), subquery.render());
    }
}
