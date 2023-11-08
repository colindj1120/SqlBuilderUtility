package com.hyperion.sqlbuilder.functionproviders;

import static com.hyperion.sqlbuilder.functionproviders.ArgsUtility.buildArgsString;

public class MySqlFunctionProvider {
    public static SqlFunctionProvider count() {
        return args -> String.format("COUNT(%s)", buildArgsString(args));
    }
    // ... other MySQL-specific functions
}

