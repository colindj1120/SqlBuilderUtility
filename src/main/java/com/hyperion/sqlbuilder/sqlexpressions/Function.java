package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.functionproviders.SqlFunctionProvider;

import java.util.Optional;

public class Function extends SqlExpression<Function> {
    private final String function;
    private final String alias;

    private Function(SqlFunctionProvider functionProvider, String alias, SqlExpression<?>... args) {
        this.function = functionProvider.provideFunction(args);
        this.alias = alias;
    }

    private Function(String function, String alias) {
        this.function = function;
        this.alias = alias;
    }

    public static Function create(SqlFunctionProvider functionProvider, SqlExpression<?>... args) {
        return new Function(functionProvider, null, args);
    }

    public static Function create(SqlFunctionProvider functionProvider, String alias, SqlExpression<?>... args) {
        return new Function(functionProvider, alias, args);
    }

    @Override
    public String render() {
        return Optional.ofNullable(alias).map(alias -> String.format("%s AS %s", function, alias)).orElse(function);
    }

    @Override
    public Function alias(String alias) {
        return new Function(this.function, alias);
    }

    @Override
    public Function self() {
        return this;
    }
}


