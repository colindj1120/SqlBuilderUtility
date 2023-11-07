package com.hyperion.sqlbuilder.sqlexpressions;

public class DefaultExpression implements SqlExpression{
    @Override
    public String render() {
        return "DEFAULT";
    }
}
