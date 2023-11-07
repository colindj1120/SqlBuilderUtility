package com.hyperion.sqlbuilder.sqlexpressions;

public class BetweenExpression implements SqlExpression {
    private final SqlExpression expression;
    private final SqlExpression startRange;
    private final SqlExpression endRange;
    private final boolean       not;

    public static BetweenExpression createBetween(SqlExpression expression, SqlExpression startRange, SqlExpression endRange) {
        return new BetweenExpression(expression, startRange, endRange, false);
    }

    public static BetweenExpression createNotBetween(SqlExpression expression, SqlExpression startRange, SqlExpression endRange) {
        return new BetweenExpression(expression, startRange, endRange, true);
    }

    private BetweenExpression(SqlExpression expression, SqlExpression startRange, SqlExpression endRange, boolean not) {
        this.expression = expression;
        this.startRange = startRange;
        this.endRange = endRange;
        this.not = not;
    }

    @Override
    public String render() {
        return String.format("%s %sBETWEEN %s AND %s",
                             expression.render(),
                             not ? "NOT " : "",
                             startRange.render(),
                             endRange.render());
    }
}

