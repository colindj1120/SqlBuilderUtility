package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

@SuppressWarnings("unused")
public class NextValueFor extends SqlExpression<NextValueFor> {
    private final String schemaName;
    private final String sql92Identifier;

    public NextValueFor(String schemaName, String sql92Identifier) {
        this.schemaName      = schemaName;
        this.sql92Identifier = sql92Identifier;
    }

    public static NextValueFor nextValueFor(String sql92Identifier) {
        return new NextValueFor(null, sql92Identifier);
    }

    public static NextValueFor nextValueFor(String schemaName, String sql92Identifier) {
        return new NextValueFor(schemaName, sql92Identifier);
    }

    @Override
    public String render() {
        return String.format("%s %s", "NEXT VALUE FOR ", Optional.ofNullable(schemaName)
                                                                 .map(str -> String.format("%s.%s", str, sql92Identifier))
                                                                 .orElse(sql92Identifier));
    }

    @Override
    public NextValueFor self() {
        return this;
    }
}
