package com.hyperion.sqlbuilder.sqlexpressions;

public class Default extends SqlExpression<Default> {
    private Default() {}

    public static Default create() {
        return new Default();
    }

    @Override
    public String render() {
        return "DEFAULT";
    }

    @Override
    public Default self() {
        return this;
    }
}
