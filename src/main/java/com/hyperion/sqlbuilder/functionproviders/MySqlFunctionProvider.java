package com.hyperion.sqlbuilder.functionproviders;

import com.hyperion.sqlbuilder.builders.MySqlBuilder;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MySqlFunctionProvider {
    public static SqlFunctionProvider<MySqlBuilder> count() {
        return args -> () -> "COUNT(" + Stream.of(args).map(SqlExpression::render).collect(Collectors.joining(", ")) + ")";
    }
    // ... other MySQL-specific functions
}

