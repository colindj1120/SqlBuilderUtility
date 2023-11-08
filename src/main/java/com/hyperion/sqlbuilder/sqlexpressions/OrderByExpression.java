package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.SortOrder;

public class OrderByExpression extends SqlExpression<OrderByExpression> {
    private OrderByExpression(String column, SortOrder sortOrder) {
        super();
        this.expression.append(column)
                       .append(sortOrder == SortOrder.NONE ? "" : String.format(" %s", sortOrder.name()));
    }

    public static OrderByExpression asc(String column) {
        return new OrderByExpression(column, SortOrder.ASC);
    }

    public static OrderByExpression desc(String column) {
        return new OrderByExpression(column, SortOrder.DESC);
    }

    public static OrderByExpression col(String column) {
        return new OrderByExpression(column, SortOrder.NONE);
    }

    @Override
    protected OrderByExpression self() {
        return this;
    }
}


