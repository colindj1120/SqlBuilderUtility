package com.hyperion.sqlbuilder.sqlexpressions;

public class Between extends SqlExpression<Between> {
    private Between(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange, boolean not) {
        super();

        this.expression.append(expression.render())
                       .append(not ? " NOT BETWEEN " : " BETWEEN ")
                       .append(startRange.render())
                       .append(" AND ")
                       .append(endRange.render());
    }

    public static Between createBetween(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange) {
        return new Between(expression, startRange, endRange, false);
    }

    public static Between createNotBetween(SqlExpression<?> expression, SqlExpression<?> startRange, SqlExpression<?> endRange) {
        return new Between(expression, startRange, endRange, true);
    }

    @Override
    protected Between self() {
        return this;
    }
}

