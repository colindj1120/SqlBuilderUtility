package com.hyperion.sqlbuilder.sqlexpressions;

public class Column extends SqlExpression<Column> {
    private Column(String columnName) {
        super();
        this.expression.append(columnName);
    }

    public static Column name(String columnName) {
        return new Column(columnName);
    }

    public static Column all() {
        return new Column("*");
    }

    @Override
    public Column as(String alias) {
        this.expression.append(" AS ")
                       .append(alias);
        return self();
    }

    @Override
    public Column tableReference(String tableName) {
        this.expression = new StringBuilder(tableName).append(".").append(this.expression);
        return self();
    }

    @Override
    protected Column self() {
        return this;
    }
}
