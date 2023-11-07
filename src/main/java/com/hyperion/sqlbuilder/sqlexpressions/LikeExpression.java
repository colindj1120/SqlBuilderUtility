package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

/**
 * Represents a SQL LIKE expression with an optional escape character.
 */
public class LikeExpression implements SqlExpression {
    private final SqlExpression field;
    private final String        pattern;
    private final String        escapeChar;
    private final boolean       not;

    public static LikeExpression createLike(SqlExpression field, String pattern, String escapeChar) {
        return new LikeExpression(field, pattern, escapeChar, false);
    }

    public static LikeExpression createNotLike(SqlExpression field, String pattern, String escapeChar) {
        return new LikeExpression(field, pattern, escapeChar, true);
    }

    private LikeExpression(SqlExpression field, String pattern, String escapeChar, boolean not) {
        this.field      = field;
        this.pattern    = pattern;
        this.escapeChar = escapeChar;
        this.not        = not;
    }

    @Override
    public String render() {
        return Optional.ofNullable(escapeChar)
                       .map(escapeChar -> String.format("%s %sLIKE '%s' ESCAPE '%s'", field.render(), not ? "NOT " : "", pattern, escapeChar))
                       .orElse(String.format("%s %sLIKE '%s'", field.render(), not ? "NOT " : "", pattern));
    }
}
