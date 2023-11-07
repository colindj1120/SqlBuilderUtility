package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

public enum DerbyOnDelete implements DerbyReferenceClause {
    NO_ACTION("NO ACTION"),
    RESTRICT("RESTRICT"),
    CASCADE("CASCADE"),
    SET_NULL("SET NULL");

    private final String action;

    DerbyOnDelete(String action) {
        this.action = action;
    }

    @Override
    public String getClause() {
        return "ON DELETE %s".formatted(action);
    }
}
