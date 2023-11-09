package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

import com.hyperion.sqlbuilder.datatypes.JoinDefinition;

public enum DerbySpecificJoin implements JoinDefinition {
    NATURAL_JOIN("NATURAL JOIN"),
    NATURAL_INNER_JOIN("NATURAL INNER JOIN"),
    NATURAL_LEFT_JOIN("NATURAL LEFT JOIN"),
    NATURAL_LEFT_OUTER_JOIN("NATURAL LEFT OUTER JOIN"),
    NATURAL_RIGHT_JOIN("NATURAL RIGHT JOIN"),
    NATURAL_RIGHT_OUTER_JOIN("NATURAL RIGHT OUTER JOIN");
    // ... other Derby-specific joins ...

    private final String sql;

    DerbySpecificJoin(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }
}
