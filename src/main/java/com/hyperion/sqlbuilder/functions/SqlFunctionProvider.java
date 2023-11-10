package com.hyperion.sqlbuilder.functions;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

@FunctionalInterface
public interface SqlFunctionProvider {
    String provideFunction(SqlExpression<?>... args);
}

