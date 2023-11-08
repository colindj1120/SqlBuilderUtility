package com.hyperion.sqlbuilder.sqlexpressions;

public class Table extends SqlExpression<Table> {
    private Table(String tableName) {
        super();
        this.expression.append(tableName);
    }

    public static Table name(String tableName) {
        return new Table(tableName);
    }

    @Override
    public Table correlation(String correlation) {
        this.expression.append(" ")
                       .append(correlation);
        return self();
    }

    @Override
    protected Table self() {
        return this;
    }
}
