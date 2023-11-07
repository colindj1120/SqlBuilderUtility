package com.hyperion.sqlbuilder.functionproviders;

import com.hyperion.sqlbuilder.builders.DerbyBuilder;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DerbyFunctionProvider {
    public static SqlFunctionProvider<DerbyBuilder> abs() {
        return args -> () -> String.format("ABS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> absval() {
        return args -> () -> String.format("ABSVAL(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> acos() {
        return args -> () -> String.format("ACOS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> asin() {
        return args -> () -> String.format("ASIN(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> atan() {
        return args -> () -> String.format("ATAN(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> atan2() {
        return args -> () -> String.format("ATAN2(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> avg() {
        return args -> () -> String.format("AVG(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> avgAll() {
        return args -> () -> String.format("AVG(ALL %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> avgDistinct() {
        return args -> () -> String.format("AVG(DISTINCT %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> bigint() {
        return args -> () -> String.format("BIGINT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> caseFunc() {
        return args -> () -> String.format("CASE\n\t%s\nEND", buildArgsStringWithNewLineTab(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> cast() {
        return args -> () -> String.format("CAST(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> ceil() {
        return args -> () -> String.format("CEIL(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> ceiling() {
        return args -> () -> String.format("CEILING(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> charFunc() {
        return args -> () -> String.format("CHAR(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> coalesce() {
        return args -> () -> String.format("COALESCE(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> concat() {
        return args -> () -> String.format(buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> cos() {
        return args -> () -> String.format("COS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> cosh() {
        return args -> () -> String.format("COSH(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> cot() {
        return args -> () -> String.format("COT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> count() {
        return args -> () -> String.format("COUNT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> countAll() {
        return args -> () -> String.format("COUNT(ALL %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> countDistinct() {
        return args -> () -> String.format("COUNT(DISTINCT %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider<DerbyBuilder> countRows() {
        return args -> () -> "COUNT(*)";
    }

    // ... other Derby-specific functions










    private static String buildArgsString(SqlExpression[] args) {
        return Stream.of(args)
                     .map(SqlExpression::render)
                     .collect(Collectors.joining(", "));
    }

    private static String buildArgsStringWithNewLineTab(SqlExpression[] args) {
        return Stream.of(args)
                     .map(SqlExpression::render)
                     .collect(Collectors.joining(",\n\t"));
    }
}

