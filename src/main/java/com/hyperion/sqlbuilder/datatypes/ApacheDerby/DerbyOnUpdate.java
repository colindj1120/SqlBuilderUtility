package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

public enum DerbyOnUpdate implements DerbyReferenceClause {
    NO_ACTION("NO ACTION"),
    RESTRICT("RESTRICT");

    private final String action;

    DerbyOnUpdate(String action) {
        this.action = action;
    }

    @Override
    public String getClause() {
        return "ON UPDATE %s".formatted(action);
    }
}
