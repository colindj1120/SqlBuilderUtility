package com.hyperion.sqlbuilder.functions;

import static com.hyperion.sqlbuilder.functions.ArgsUtility.buildArgsString;

@SuppressWarnings("unused")
public interface DerbyFunctions extends SqlFunctions {
    DerbyFunctions concat = args -> String.format(buildArgsString(args));
}

