package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

/**
 * Represents a SQL LIKE expression with an optional escape character.
 */
@SuppressWarnings("unused")
public class Like extends SqlExpression<Like> {
    private final SqlExpression<?> field;
    private final String        pattern;
    private final String        escapeChar;
    private final boolean       not;

    private Like(SqlExpression<?> field, String pattern, String escapeChar, boolean not) {
        this.field      = field;
        this.pattern    = pattern;
        this.escapeChar = escapeChar;
        this.not        = not;
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
    public String render() {
        String escape = Optional.ofNullable(escapeChar)
                                .map(str -> String.format(" ESCAPE '%s'", escapeChar))
                                .orElse("");
        return String.format("%s %s '%s'%s", field.render(), not ? "NOT LIKE" : "LIKE", pattern, escape);
    }

    @Override
    public Like self() {
        return this;
    }
}
