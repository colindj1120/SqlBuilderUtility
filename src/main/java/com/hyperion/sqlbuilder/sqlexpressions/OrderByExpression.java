package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.SqlStandard.SortOrder;

@SuppressWarnings("unused")
public class OrderByExpression extends SqlExpression<OrderByExpression> {
    private final Column column;
    private final SortOrder sortOrder;

    private OrderByExpression(Column column, SortOrder sortOrder) {
        this.column = column;
        this.sortOrder = sortOrder;
    }

    public static OrderByExpression asc(Column column) {
        return new OrderByExpression(column, SortOrder.ASC);
    }

    public static OrderByExpression desc(Column column) {
        return new OrderByExpression(column, SortOrder.DESC);
    }

    public static OrderByExpression column(Column column) {
        return new OrderByExpression(column, SortOrder.NONE);
    }

    @Override
    public String render() {
        String name = column.hasAlias() ? column.getAlias() : column.render();
        return String.format("%s%s", name, sortOrder == SortOrder.NONE ? "" : String.format(" %s", sortOrder.name()));
    }

    @Override
    public OrderByExpression self() {
        return this;
    }
}


