package com.hyperion.sqlbuilder.datatypes.SqlStandard;

public enum SortOrder {
    ASC("ASC"),
    DESC("DESC"),
    NONE(""); // Represents default sorting (usually ASC)

    private final String sql;

    SortOrder(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return this.sql;
    }
}

