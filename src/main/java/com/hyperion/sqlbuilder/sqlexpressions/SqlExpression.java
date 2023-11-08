package com.hyperion.sqlbuilder.sqlexpressions;

public abstract class SqlExpression<T extends SqlExpression<T>> {
    protected StringBuilder expression;

    public SqlExpression() {
        expression = new StringBuilder();
    }

    public String render() {
        return expression.toString();
    }

    public T as(String alias) {
        unsupportedAs(self());
        return self();
    }

    protected void unsupportedAs(T clazz) {
        throw new UnsupportedOperationException(String.format("%s operation does not support AS alias", clazz.getClass()
                                                                                                             .getSimpleName()));
    }

    protected abstract T self();

    public T correlation(String correlation) {
        unsupportedCorrelation(self());
        return self();
    }

    protected void unsupportedCorrelation(T clazz) {
        throw new UnsupportedOperationException(String.format("%s operation does not support a correlation alias", clazz.getClass()
                                                                                                                        .getSimpleName()));
    }

    public T tableReference(String tableName) {
        unsupportedTableReference(self());
        return self();
    }

    protected void unsupportedTableReference(T clazz) {
        throw new UnsupportedOperationException(String.format("%s operation does not support a table reference", clazz.getClass()
                                                                                                                      .getSimpleName()));
    }

//    default SqlExpression as(String alias) {
//        return () -> String.format("%s AS %s", this.render(), alias);
//    }

//    default SqlExpression correlation(String correlation)  {
//        return () -> String.format("%s %s", this.render(), correlation);
//    }
//
//    default SqlExpression tableReference(String tableName) {
//        return () -> String.format("%s.%s", tableName, this.render());
//    }

    // Static method to represent all columns
}

