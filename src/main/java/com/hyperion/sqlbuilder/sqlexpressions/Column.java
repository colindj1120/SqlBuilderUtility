package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
public class Column extends SqlExpression<Column> {
    private final String columnName;
    private final String alias;
    private final String table;

    private Column(String columnName, String alias, String table) {
        this.columnName = columnName;
        this.alias      = alias;
        this.table      = table;
    }

    public static Column name(String columnName) {
        return new Column(columnName, null, null);
    }

    public static Column nameWithAlias(String columnName, String alias)
    {
        return new Column(columnName, alias, null);
    }

    public static Column nameWithTable(String columnName, String table) {
        return new Column(columnName, null, table);
    }

    public static Column nameWithAliasAndTable(String columnName, String alias, String reference) {
        return new Column(columnName, alias, reference);
    }

    public static Column all() {
        return new Column("*", null, null);
    }

    @Override
    public String render() {
        String render = Optional.ofNullable(table)
                                .map(reference -> String.format("%s.%s", reference, columnName))
                                .orElse(columnName);
        render += Optional.ofNullable(alias)
                          .map(alias -> String.format(" AS %s", alias))
                          .orElse("");
        return render;
    }

    @Override
    public Column alias(String alias) {
        return new Column(this.columnName, alias, this.table);
    }

    @Override
    public Column reference(String reference) {
        return new Column(this.columnName, this.alias, reference);
    }

    @Override
    public Column self() {
        return this;
    }

    public boolean hasAlias() {
        return Objects.nonNull(alias);
    }

    public String getAlias() {
        return alias;
    }

    public Column nameOnly() {
        return new Column(this.columnName, null, null);
    }

    public Column aliasOnly() {
        return new Column(this.alias, null, null);
    }

    public Column nameWithReference() {
        return new Column(this.columnName, null, this.table);
    }
}
