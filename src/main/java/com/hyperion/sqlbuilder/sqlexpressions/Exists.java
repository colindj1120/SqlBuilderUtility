package com.hyperion.sqlbuilder.sqlexpressions;

public class Exists extends SqlExpression<Exists> {
    private Exists(Subquery subquery, boolean not) {
        super();
        this.expression.append(not ? "NOT EXISTS " : "EXISTS ")
                       .append(subquery.render());
    }

    public static Exists createExists(Subquery subquery) {
        return new Exists(subquery, false);
    }

    public static Exists createNotExists(Subquery subquery) {
        return new Exists(subquery, true);
    }

    @Override
    protected Exists self() {
        return this;
    }
}

