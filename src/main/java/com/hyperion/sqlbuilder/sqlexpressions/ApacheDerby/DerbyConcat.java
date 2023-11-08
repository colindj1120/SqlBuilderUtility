package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConcat extends SqlExpression<DerbyConcat> {
    private DerbyConcat(String[] parts) {
        super();
        String concatenatedParts = Arrays.stream(parts)
                                         .map(part -> "'" + part.replace("'", "''") + "'") // Handle single quotes in SQL
                                         .collect(Collectors.joining(" || "));
        this.expression.append(concatenatedParts);
    }

    public static DerbyConcat create(String... parts) {
        return new DerbyConcat(parts);
    }

    @Override
    protected DerbyConcat self() {
        return this;
    }
}
