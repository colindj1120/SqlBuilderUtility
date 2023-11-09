package com.hyperion.sqlbuilder.sqlexpressions;

public abstract class SqlExpression<T extends SqlExpression<T>> {
    public abstract String render();

    public T alias(String alias) {
        throw new UnsupportedOperationException(String.format("Alias is not supported by %s", self().getClass().getSimpleName()));
    }

    public T correlation(String correlation) {
        throw new UnsupportedOperationException(String.format("Correlation is not supported by %s", self().getClass().getSimpleName()));
    }

    public T reference(String reference){
        throw new UnsupportedOperationException(String.format("Reference is not supported by %s", self().getClass().getSimpleName()));
    }

    public abstract T self();
}


