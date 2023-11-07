package com.hyperion.sqlbuilder.sqlexpressions;

public class ColumnExpression implements SqlExpression {
    private final String columnName;

    public static ColumnExpression create(String columnName) {
        return new ColumnExpression(columnName);
    }

    private ColumnExpression(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String render() {
        return columnName;
    }
}
