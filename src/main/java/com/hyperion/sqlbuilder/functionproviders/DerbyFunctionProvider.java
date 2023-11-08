package com.hyperion.sqlbuilder.functionproviders;

import static com.hyperion.sqlbuilder.functionproviders.ArgsUtility.buildArgsString;
import static com.hyperion.sqlbuilder.functionproviders.ArgsUtility.buildArgsStringWithNewLineTab;

public class DerbyFunctionProvider {
    public static SqlFunctionProvider abs() {
        return args -> String.format("ABS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider absval() {
        return args -> String.format("ABSVAL(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider acos() {
        return args -> String.format("ACOS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider asin() {
        return args -> String.format("ASIN(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider atan() {
        return args -> String.format("ATAN(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider atan2() {
        return args -> String.format("ATAN2(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider avg() {
        return args -> String.format("AVG(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider avgAll() {
        return args -> String.format("AVG(ALL %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider avgDistinct() {
        return args -> String.format("AVG(DISTINCT %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider bigint() {
        return args -> String.format("BIGINT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider caseFunc() {
        return args -> String.format("CASE\n\t%s\nEND", buildArgsStringWithNewLineTab(args));
    }

    public static SqlFunctionProvider cast() {
        return args -> String.format("CAST(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider ceil() {
        return args -> String.format("CEIL(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider ceiling() {
        return args -> String.format("CEILING(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider charFunc() {
        return args -> String.format("CHAR(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider coalesce() {
        return args -> String.format("COALESCE(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider concat() {
        return args -> String.format(buildArgsString(args));
    }

    public static SqlFunctionProvider cos() {
        return args -> String.format("COS(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider cosh() {
        return args -> String.format("COSH(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider cot() {
        return args -> String.format("COT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider count() {
        return args -> String.format("COUNT(%s)", buildArgsString(args));
    }

    public static SqlFunctionProvider countAll() {
        return args -> String.format("COUNT(ALL %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider countDistinct() {
        return args -> String.format("COUNT(DISTINCT %s)", buildArgsString(args));
    }

    public static SqlFunctionProvider countRows() {
        return args -> "COUNT(*)";
    }

    // ... other Derby-specific functions
}

