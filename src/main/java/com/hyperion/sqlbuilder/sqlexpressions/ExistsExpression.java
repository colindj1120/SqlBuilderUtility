package com.hyperion.sqlbuilder.sqlexpressions;

public class ExistsExpression implements SqlExpression {
    private final SubqueryExpression subquery;
    private final boolean not;

    public static ExistsExpression createExists(SubqueryExpression subquery) {
        return new ExistsExpression(subquery, false);
    }

    public static ExistsExpression createNotExists(SubqueryExpression subquery) {
        return new ExistsExpression(subquery, true);
    }

    private ExistsExpression(SubqueryExpression subquery, boolean not) {
        this.subquery = subquery;
        this.not = not;
    }

    @Override
    public String render() {
        return String.format("%sEXISTS %s",
                             not ? "NOT " : "",
                             subquery.render());
    }
}

