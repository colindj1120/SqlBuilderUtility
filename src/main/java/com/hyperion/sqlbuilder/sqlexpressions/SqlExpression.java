package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface SqlExpression {
    String render();
    default SqlExpression as(String alias) {
        return () -> this.render() + " AS " + alias;
    }

    default SqlExpression correlation(String correlation) {return () -> this.render() + " " + correlation; }

    // Static method to represent all columns
    static SqlExpression allColumns() {
        return () -> "*";
    }
    // Static method for DISTINCT
    static SqlExpression distinct(SqlExpression... expressions) {
        String renderedExpressions = Stream.of(expressions)
                                           .map(SqlExpression::render)
                                           .collect(Collectors.joining(", "));
        return () -> "DISTINCT " + renderedExpressions;
    }
}

