package com.hyperion.sqlbuilder.datatypes.SqlStandard;

/**
 * Enum for SQL quantifiers ALL, ANY, and SOME.
 */
public enum Quantifier {
    ALL("ALL"),
    ANY("ANY"),
    SOME("SOME");

    private final String keyword;

    Quantifier(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }
}
