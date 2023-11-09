package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

public enum DerbyOnUpdate implements DerbyReferenceClause {
    ON_UPDATE_NO_ACTION("NO ACTION"),
    ON_UPDATE_RESTRICT("RESTRICT");

    private final String action;

    DerbyOnUpdate(String action) {
        this.action = action;
    }

    @Override
    public String getClause() {
        return "ON UPDATE %s".formatted(action);
    }
}
