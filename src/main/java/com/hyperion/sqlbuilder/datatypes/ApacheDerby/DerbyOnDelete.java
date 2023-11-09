package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

public enum DerbyOnDelete implements DerbyReferenceClause {
    ON_DELETE_NO_ACTION("NO ACTION"),
    ON_DELETE_RESTRICT("RESTRICT"),
    ON_DELETE_CASCADE("CASCADE"),
    ON_DELETE_SET_NULL("SET NULL");

    private final String action;

    DerbyOnDelete(String action) {
        this.action = action;
    }

    @Override
    public String getClause() {
        return "ON DELETE %s".formatted(action);
    }
}
