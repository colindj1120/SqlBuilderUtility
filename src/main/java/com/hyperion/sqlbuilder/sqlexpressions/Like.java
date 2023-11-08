package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

/**
 * Represents a SQL LIKE expression with an optional escape character.
 */
public class Like extends SqlExpression<Like> {
    private Like(SqlExpression<?> field, String pattern, String escapeChar, boolean not) {
        super();
        String escape = Optional.ofNullable(escapeChar)
                                .map(str -> String.format(" ESCAPE '%s'", escapeChar))
                                .orElse("");
        this.expression.append(field.render())
                       .append(not ? " NOT LIKE '" : " LIKE '")
                       .append(pattern)
                       .append("'")
                       .append(escape);
    }

    public static Like createLike(SqlExpression<?> field, String pattern) {
        return new Like(field, pattern, null, false);
    }

    public static Like createLike(SqlExpression<?> field, String pattern, String escapeChar) {
        return new Like(field, pattern, escapeChar, false);
    }

    public static Like createNotLike(SqlExpression<?> field, String pattern) {
        return new Like(field, pattern, null, true);
    }

    public static Like createNotLike(SqlExpression<?> field, String pattern, String escapeChar) {
        return new Like(field, pattern, escapeChar, true);
    }

    @Override
    protected Like self() {
        return this;
    }
}
