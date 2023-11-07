package com.hyperion.sqlbuilder.sqlexpressions;

public class ConstantExpression implements SqlExpression{
    private final String constant;

    public static ConstantExpression createStrConst(String constant) {
        return new ConstantExpression(String.format("'%s'", constant));
    }

    public static ConstantExpression createNumExpr(String numericalExpression) {
        return new ConstantExpression(numericalExpression);
    }

    public static ConstantExpression createNumConst(Number number) {
        return new ConstantExpression(number.toString());
    }

    private ConstantExpression(String constant) {
        this.constant = constant;
    }

    @Override
    public String render() {
        return constant;
    }
}
