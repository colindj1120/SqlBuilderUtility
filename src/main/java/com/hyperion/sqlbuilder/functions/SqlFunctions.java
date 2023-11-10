package com.hyperion.sqlbuilder.functions;

import static com.hyperion.sqlbuilder.functions.ArgsUtility.buildArgsString;
import static com.hyperion.sqlbuilder.functions.ArgsUtility.buildArgsStringWithNewLineTab;

@SuppressWarnings("unused")
public interface SqlFunctions extends SqlFunctionProvider {
    SqlFunctions ABS = args -> String.format("ABS(%s)", buildArgsString(args));
    SqlFunctions ABSVAL = args -> String.format("ABSVAL(%s)", buildArgsString(args));
    SqlFunctions ACOS = args -> String.format("ACOS(%s)", buildArgsString(args));
    SqlFunctions asin = args -> String.format("ASIN(%s)", buildArgsString(args));
    SqlFunctions atan = args -> String.format("ATAN(%s)", buildArgsString(args));
    SqlFunctions atan2 = args -> String.format("ATAN2(%s)", buildArgsString(args));
    SqlFunctions avg = args -> String.format("AVG(%s)", buildArgsString(args));
    SqlFunctions avgAll = args -> String.format("AVG(ALL %s)", buildArgsString(args));
    SqlFunctions avgDistinct = args -> String.format("AVG(DISTINCT %s)", buildArgsString(args));
    SqlFunctions bigint = args -> String.format("BIGINT(%s)", buildArgsString(args));
    SqlFunctions caseFunc = args -> String.format("CASE\n\t%s\nEND", buildArgsStringWithNewLineTab(args));
    SqlFunctions cast = args -> String.format("CAST(%s)", buildArgsString(args));
    SqlFunctions ceil = args -> String.format("CEIL(%s)", buildArgsString(args));
    SqlFunctions ceiling = args -> String.format("CEILING(%s)", buildArgsString(args));
    SqlFunctions charFunc = args -> String.format("CHAR(%s)", buildArgsString(args));
    SqlFunctions coalesce = args -> String.format("COALESCE(%s)", buildArgsString(args));
    SqlFunctions cos = args -> String.format("COS(%s)", buildArgsString(args));
    SqlFunctions cosh = args -> String.format("COSH(%s)", buildArgsString(args));
    SqlFunctions cot = args -> String.format("COT(%s)", buildArgsString(args));
    SqlFunctions count = args -> String.format("COUNT(%s)", buildArgsString(args));
    SqlFunctions countAll = args -> String.format("COUNT(ALL %s)", buildArgsString(args));
    SqlFunctions countDistinct = args -> String.format("COUNT(DISTINCT %s)", buildArgsString(args));
    SqlFunctions countRows = args -> "COUNT(*)";
}
