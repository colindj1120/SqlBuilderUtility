package com.hyperion.sqlbuilder.functionproviders;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

@FunctionalInterface
public interface SqlFunctionProvider {
    String provideFunction(SqlExpression<?>... args);
}

