package com.hyperion.sqlbuilder.sqlexpressions;
import com.hyperion.sqlbuilder.datatypes.SqlStandard.SortOrder;

//This doesn't implement SqlExpression because its specific to ORDER BY clause
public class OrderByExpression {
    private final String    column;
    private final SortOrder sortOrder;

    public OrderByExpression(String column, SortOrder sortOrder) {
        this.column = column;
        this.sortOrder = sortOrder;
    }

    public String render() {
        return column + (sortOrder == SortOrder.NONE ? "" : " " + sortOrder);
    }

    // Static factory methods for convenience
    public static OrderByExpression asc(String column) {
        return new OrderByExpression(column, SortOrder.ASC);
    }

    public static OrderByExpression desc(String column) {
        return new OrderByExpression(column, SortOrder.DESC);
    }

    public static OrderByExpression col(String column) {
        return new OrderByExpression(column, SortOrder.NONE);
    }
}


