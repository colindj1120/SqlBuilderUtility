package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class Exists extends SqlExpression<Exists> {
    private final Subquery subquery;
    private final boolean not;

    private Exists(Subquery subquery, boolean not) {
        this.subquery = subquery;
        this.not = not;
    }

    public static Exists exists(Subquery subquery) {
        return new Exists(subquery, false);
    }

    public static Exists notExists(Subquery subquery) {
        return new Exists(subquery, true);
    }

    @Override
    public String render() {
        return String.format("%s %s", not ? "NOT EXISTS" : "EXISTS", subquery.render());
    }

    @Override
    public Exists self() {
        return this;
    }
}

