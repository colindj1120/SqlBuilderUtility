package com.hyperion.sqlbuilder.sqlexpressions;

public class TableExpression implements SqlExpression {
    private final String tableName;

    public static TableExpression create(String tableName) {
        return new TableExpression(tableName);
    }

    private TableExpression(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String render() {
        return tableName;
    }
}
