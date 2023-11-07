package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConcatExpression implements SqlExpression {
    private final String concatenatedParts;

    private DerbyConcatExpression(String[] parts) {
        this.concatenatedParts = Arrays.stream(parts)
                                       .map(part -> "'" + part.replace("'", "''") + "'") // Handle single quotes in SQL
                                       .collect(Collectors.joining(" || "));
    }

    public static DerbyConcatExpression create(String... parts) {
        return new DerbyConcatExpression(parts);
    }

    @Override
    public String render() {
        return concatenatedParts;
    }
}
