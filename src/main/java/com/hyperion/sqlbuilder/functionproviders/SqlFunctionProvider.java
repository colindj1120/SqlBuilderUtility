package com.hyperion.sqlbuilder.functionproviders;

import com.hyperion.sqlbuilder.builders.SqlBuilder;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

@FunctionalInterface
public interface SqlFunctionProvider<T extends SqlBuilder<T>> {
    SqlExpression provideFunction(SqlExpression... args);
}

