package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.functionproviders.SqlFunctionProvider;

public class FunctionExpression implements SqlExpression {
    private final SqlFunctionProvider<?> functionProvider;
    private final SqlExpression[] args;

    public static FunctionExpression create(SqlFunctionProvider<?> functionProvider, SqlExpression... args) {
        return new FunctionExpression(functionProvider, args);
    }

    private FunctionExpression(SqlFunctionProvider<?> functionProvider, SqlExpression... args) {
        this.functionProvider = functionProvider;
        this.args = args;
    }

    @Override
    public String render() {
        return functionProvider.provideFunction(args).render();
    }
}


