package com.hyperion.sqlbuilder.sqlexpressions;

public class Subquery extends SqlExpression<Subquery> {
    private Subquery(String query) {
        super();
        this.expression.append("(")
                       .append(query)
                       .append(")");
    }

    public static Subquery query(String query) {
        return new Subquery(query);
    }

    @Override
    public Subquery as(String alias) {
        this.expression.append(" AS ")
                       .append(alias);
        return self();
    }

    @Override
    protected Subquery self() {
        return this;
    }
}

