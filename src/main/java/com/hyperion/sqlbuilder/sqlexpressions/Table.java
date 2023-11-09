package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

public class Table extends SqlExpression<Table> {
    private final String tableName;
    private final String correlation;
    private final String alias;

    private Table(String tableName, String correlation, String alias) {
        this.tableName   = tableName;
        this.correlation = correlation;
        this.alias       = alias;
    }

    public static Table name(String tableName) {
        return new Table(tableName, null, null);
    }

    public static Table name(String tableName, String correlationOrAlias, boolean isAlias) {
        return isAlias ? new Table(tableName, null, correlationOrAlias) : new Table(tableName, correlationOrAlias, null);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias)
                       .map(alias -> String.format("%s AS %s", tableName, alias))
                       .orElse(String.format("%s%s", tableName, Optional.ofNullable(correlation)
                                                                        .map(str -> String.format(" %s", str))
                                                                        .orElse("")));
    }

    @Override
    public Table correlation(String correlation) {
        return new Table(this.tableName, correlation, null);
    }

    @Override
    public Table alias(String alias) {
        return new Table(this.tableName, null, alias);
    }

    @Override
    public Table self() {
        return this;
    }

    public Table nameOnly() {
        return new Table(this.tableName, null, null);
    }
}
