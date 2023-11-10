package com.hyperion.sqlbuilder.sqlexpressions;

@SuppressWarnings("unused")
public class TableFunctionInvocation extends SqlExpression<TableFunctionInvocation> {
    private final Function function;
    private final String correlation;

    public TableFunctionInvocation(Function function, String correlation) {
        this.function = function;
        this.correlation = correlation;
    }

    public static TableFunctionInvocation tableFunctionInvocation(Function function, String correlation) {
        return new TableFunctionInvocation(function, correlation);
    }

    @Override
    public String render() {
        return String.format("TABLE(%s) %s", function.render(), correlation);
    }

    @Override
    public TableFunctionInvocation self() {
        return this;
    }
}
