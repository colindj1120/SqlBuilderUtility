package com.hyperion.sqlbuilder.sqlexpressions;

public class ParameterPlaceholder extends SqlExpression<ParameterPlaceholder> {
    private ParameterPlaceholder() {}

    public static ParameterPlaceholder get() {
        return new ParameterPlaceholder();
    }

    @Override
    public String render() {
        return "?";
    }

    @Override
    public ParameterPlaceholder self() {
        return this;
    }
}
