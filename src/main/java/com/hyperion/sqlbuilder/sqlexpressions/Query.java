package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class Query extends SqlExpression<Query> {
    private final String query;

    private Query(String query) {
        this.query = query;
    }

    public static Query query(String query) {
        return new Query(query);
    }

    @Override
    public String render() {
        return query;
    }

    @Override
    public Query self() {
        return this;
    }
}


