package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Objects;
import java.util.Optional;

public class Column extends SqlExpression<Column> {
    private final String columnName;
    private final String alias;
    private final String reference;

    private Column(String columnName, String alias, String reference) {
        this.columnName = columnName;
        this.alias = alias;
        this.reference = reference;
    }

    public static Column name(String columnName) {
        return new Column(columnName, null, null);
    }

    public static Column name(String columnName, String aliasOrReference, boolean isAlias) {
        if(isAlias) {
            return new Column(columnName, aliasOrReference, null);
        } else {
            return new Column(columnName, null, aliasOrReference);
        }
    }

    public static Column name(String columnName, String alias, String reference) {
        return new Column(columnName, alias, reference);
    }


    public static Column all() {
        return new Column("*", null, null);
    }

    @Override
    public String render() {
        String render = Optional.ofNullable(reference).map(reference -> String.format("%s.%s",reference, columnName)).orElse(columnName);
        render += Optional.ofNullable(alias).map(alias -> String.format(" AS %s", alias)).orElse("");
        return render;
    }

    @Override
    public Column alias(String alias) {
        return new Column(this.columnName, alias, this.reference);
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

    public Column onlyName() {
        return new Column(this.columnName, null, null);
    }

    public Column onlyAlias() {
        return new Column(this.alias, null, null);
    }

    public Column withoutAlias() {
        return new Column(this.columnName, null, this.reference);
    }
}
