package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConcat extends SqlExpression<DerbyConcat> {
    private final String concatenatedParts;

    private DerbyConcat(String[] parts) {
        this.concatenatedParts = Arrays.stream(parts)
                                       .map(part -> "'" + part.replace("'", "''") + "'") // Handle single quotes in SQL
                                       .collect(Collectors.joining(" || "));
    }

    public static DerbyConcat create(String... parts) {
        return new DerbyConcat(parts);
    }

    @Override
    public String render() {
        return concatenatedParts;
    }

    @Override
    public DerbyConcat self() {
        return this;
    }
}
