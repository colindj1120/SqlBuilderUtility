package com.hyperion.sqlbuilder.sqlexpressions;

public class Default extends SqlExpression<Default> {
    private Default() {
        super();
        this.expression.append("DEFAULT");
    }

    public static Default create() {
        return new Default();
    }

    @Override
    protected Default self() {
        return this;
    }
}
