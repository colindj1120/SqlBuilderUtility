package com.hyperion.sqlbuilder.functionproviders;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArgsUtility {
    public static String buildArgsString(SqlExpression<?>[] args) {
        return Stream.of(args)
                     .map(SqlExpression::render)
                     .collect(Collectors.joining(", "));
    }

    public static String buildArgsStringWithNewLineTab(SqlExpression<?>[] args) {
        return Stream.of(args)
                     .map(SqlExpression::render)
                     .collect(Collectors.joining(",\n\t"));
    }
}
