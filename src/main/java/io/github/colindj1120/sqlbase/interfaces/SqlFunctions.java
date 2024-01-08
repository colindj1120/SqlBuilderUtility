/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of SqlBuilderUtility (https://github.com/colindj1120/SqlBuilderUtility).
 *
 * SqlBuilderUtility is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SqlBuilderUtility is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SqlBuilderUtility.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.sqlbase.interfaces;

import io.github.colindj1120.utils.ArgsUtility;

public interface SqlFunctions extends SqlFunctionProvider {
    SqlFunctions ABS           = args -> String.format("ABS(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions ABSVAL        = args -> String.format("ABSVAL(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions ACOS          = args -> String.format("ACOS(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions asin          = args -> String.format("ASIN(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions atan          = args -> String.format("ATAN(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions atan2         = args -> String.format("ATAN2(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions avg           = args -> String.format("AVG(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions avgAll        = args -> String.format("AVG(ALL %s)", ArgsUtility.buildArgsString(args));
    SqlFunctions avgDistinct   = args -> String.format("AVG(DISTINCT %s)", ArgsUtility.buildArgsString(args));
    SqlFunctions bigint        = args -> String.format("BIGINT(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions caseFunc      = args -> String.format("CASE%sEND", ArgsUtility.buildArgsString(args));
    SqlFunctions cast          = args -> String.format("CAST(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions ceil          = args -> String.format("CEIL(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions ceiling       = args -> String.format("CEILING(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions charFunc      = args -> String.format("CHAR(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions coalesce      = args -> String.format("COALESCE(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions cos           = args -> String.format("COS(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions cosh          = args -> String.format("COSH(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions cot           = args -> String.format("COT(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions count         = args -> String.format("COUNT(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions countAll      = args -> String.format("COUNT(ALL %s)", ArgsUtility.buildArgsString(args));
    SqlFunctions countDistinct = args -> String.format("COUNT(DISTINCT %s)", ArgsUtility.buildArgsString(args));
    SqlFunctions countStar     = args -> "COUNT(*)";
    SqlFunctions lower         = args -> String.format("LOWER(%s)", ArgsUtility.buildArgsString(args));
    SqlFunctions max           = args -> String.format("MAX(%s)", ArgsUtility.buildArgsString(args));
}
