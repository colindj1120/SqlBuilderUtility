package com.hyperion.sqlbuilder.datatypes.SqlStandard;

import com.hyperion.sqlbuilder.datatypes.JoinDefinition;

public enum StandardJoin implements JoinDefinition {
    JOIN("JOIN"),
    INNER("INNER JOIN"),
    LEFT("LEFT JOIN"),
    LEFT_OUTER("LEFT OUTER JOIN"),
    RIGHT("RIGHT JOIN"),
    RIGHT_OUTER("RIGHT OUTER JOIN"),
    CROSS("CROSS JOIN");

    private final String sql;

    StandardJoin(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }
}
