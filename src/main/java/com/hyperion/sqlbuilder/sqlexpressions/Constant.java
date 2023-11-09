package com.hyperion.sqlbuilder.sqlexpressions;

public class Constant extends SqlExpression<Constant> {
    private final String constant;

    private Constant(String constant) {
        this.constant = constant;
    }

    public static Constant createStrConst(String constant) {
        return new Constant(String.format("'%s'", constant));
    }

    public static Constant createNumExpr(String numericalExpression) {
        return new Constant(numericalExpression);
    }

    public static Constant createNumConst(Number number) {
        return new Constant(number.toString());
    }

    @Override
    public String render() {
        return constant;
    }

    @Override
    public Constant self() {
        return this;
    }
}
