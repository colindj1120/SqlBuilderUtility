package com.hyperion.sqlbuilder.datatypes.SqlStandard;

import com.hyperion.sqlbuilder.datatypes.JoinDefinition;

public enum StandardJoin implements JoinDefinition {
    JOIN("JOIN"),
    INNER_JOIN("INNER JOIN"),
    LEFT_JOIN("LEFT JOIN"),
    LEFT_OUTER_JOIN("LEFT OUTER JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    RIGHT_OUTER_JOIN("RIGHT OUTER JOIN"),
    CROSS_JOIN("CROSS JOIN");

    private final String sql;

    StandardJoin(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSql() {
        return sql;
    }
}
