package com.hyperion.sqlbuilder.sqlexpressions;

import java.util.Optional;

@SuppressWarnings("unused")
public class View extends SqlExpression<View> {
    private final String schemaName;
    private final String sql92Identifier;

    public View(String schemaName, String sql92Identifier) {
        this.schemaName      = schemaName;
        this.sql92Identifier = sql92Identifier;
    }

    public static View view(String sql92Identifier) {
        return new View(null, sql92Identifier);
    }

    public static View view(String schemaName, String sql92Identifier) {
        return new View(schemaName, sql92Identifier);
    }

    @Override
    public String render() {
        return Optional.ofNullable(schemaName)
                       .map(str -> String.format("%s.%s", str, sql92Identifier))
                       .orElse(sql92Identifier);
    }

    @Override
    public View self() {
        return this;
    }
}
