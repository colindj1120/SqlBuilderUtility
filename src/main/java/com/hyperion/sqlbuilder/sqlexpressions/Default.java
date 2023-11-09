package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class Default extends SqlExpression<Default> {
    private Default() {}

    public static Default defaultExpr() {
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
