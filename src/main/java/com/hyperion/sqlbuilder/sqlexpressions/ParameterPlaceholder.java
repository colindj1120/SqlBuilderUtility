package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class ParameterPlaceholder extends SqlExpression<ParameterPlaceholder> {
    private ParameterPlaceholder() {}

    public static ParameterPlaceholder parameterPlaceholder() {
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
