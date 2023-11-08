package com.hyperion.sqlbuilder.sqlexpressions;

public class Constant extends SqlExpression<Constant> {
    private Constant(String constant) {
        super();
        this.expression.append(constant);
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
    protected Constant self() {
        return this;
    }
}
