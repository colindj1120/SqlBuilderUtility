package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

@SuppressWarnings("unused")
public class Subquery extends SqlExpression<Subquery> {
    private final String query;
    private final String alias;

    private Subquery(String query, String alias) {
        this.query = query;
        this.alias = alias;
    }

    public static Subquery subquery(String query) {
        return new Subquery(query, null);
    }

    public static Subquery subquery(String query, String alias) {
        return new Subquery(query, alias);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias)
                       .map(alias -> String.format("(%s) AS %s", query, alias))
                       .orElse(String.format("(%s)", query));
    }

    @Override
    public Subquery alias(String alias) {
        return new Subquery(this.query, alias);
    }

    @Override
    public Subquery self() {
        return this;
    }
}

