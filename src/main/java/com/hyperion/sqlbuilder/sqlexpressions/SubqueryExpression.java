package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.builders.SqlBuilder;

public class SubqueryExpression implements SqlExpression {
    private final SqlBuilder<?> subqueryBuilder;

    public static SubqueryExpression create(SqlBuilder<?> subqueryBuilder) {
        return new SubqueryExpression(subqueryBuilder);
    }

    private SubqueryExpression(SqlBuilder<?> subqueryBuilder) {
        this.subqueryBuilder = subqueryBuilder;
    }

    @Override
    public String render() {
        return "(" + subqueryBuilder.build() + ")";
    }
}

