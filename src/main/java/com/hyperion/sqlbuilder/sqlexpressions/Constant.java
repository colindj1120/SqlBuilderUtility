package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class Constant extends SqlExpression<Constant> {
    private final String constant;

    private Constant(String constant) {
        this.constant = constant;
    }

    public static Constant strConst(String constant) {
        return new Constant(String.format("'%s'", constant));
    }

    public static Constant numExprConst(String numericalExpression) {
        return new Constant(numericalExpression);
    }

    public static Constant numConst(Number number) {
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
