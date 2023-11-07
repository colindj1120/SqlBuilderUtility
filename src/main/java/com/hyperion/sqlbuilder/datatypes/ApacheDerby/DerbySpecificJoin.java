package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

import com.hyperion.sqlbuilder.datatypes.JoinDefinition;

public enum DerbySpecificJoin implements JoinDefinition {
    NATURAL("NATURAL JOIN"),
    NATURAL_INNER("NATURAL INNER JOIN"),
    NATURAL_LEFT("NATURAL LEFT JOIN"),
    NATURAL_LEFT_OUTER("NATURAL LEFT OUTER JOIN"),
    NATURAL_RIGHT("NATURAL RIGHT JOIN"),
    NATURAL_RIGHT_OUTER("NATURAL RIGHT OUTER JOIN");
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
