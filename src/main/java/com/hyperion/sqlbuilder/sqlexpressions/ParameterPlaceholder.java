package com.hyperion.sqlbuilder.sqlexpressions;

public class ParameterPlaceholder extends SqlExpression<ParameterPlaceholder> {
    private ParameterPlaceholder() {
        super();
        this.expression.append("?");
    }

    public static ParameterPlaceholder create() {
        return new ParameterPlaceholder();
    }

    @Override
    protected ParameterPlaceholder self() {
        return this;
    }
}
