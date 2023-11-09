package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

public class Table extends SqlExpression<Table> {
    private final String tableName;
    private final String correlation;

    private Table(String tableName, String correlation) {
        this.tableName = tableName;
        this.correlation = correlation;
    }

    public static Table name(String tableName) {
        return new Table(tableName, null);
    }

    public static Table name(String tableName, String correlation) {
        return new Table(tableName, correlation);
    }

    @Override
    public String render() {
        return String.format("%s%s", tableName, Optional.ofNullable(correlation)
                                                        .map(str -> String.format(" %s", str))
                                                        .orElse(""));
    }

    @Override
    public Table correlation(String correlation) {
        return new Table(this.tableName, correlation);
    }

    @Override
    public Table self() {
        return this;
    }
}
