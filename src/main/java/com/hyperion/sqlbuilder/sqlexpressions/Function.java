package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.functionproviders.SqlFunctionProvider;

public class Function extends SqlExpression<Function> {
    private Function(SqlFunctionProvider functionProvider, SqlExpression<?>... args) {
        super();
        this.expression.append(functionProvider.provideFunction(args));
    }

    public static Function create(SqlFunctionProvider functionProvider, SqlExpression<?>... args) {
        return new Function(functionProvider, args);
    }

    @Override
    public Function as(String alias) {
        this.expression.append(" AS ")
                       .append(alias);
        return self();
    }

    @Override
    protected Function self() {
        return this;
    }
}


