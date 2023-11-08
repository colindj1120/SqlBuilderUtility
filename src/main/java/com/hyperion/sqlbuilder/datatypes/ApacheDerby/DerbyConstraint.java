package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConstraint {
    private String constraint;

    private DerbyConstraint(String constraint) {
        this.constraint = constraint;
    }

    // Factory method for creating a new DerbyConstraint instance
    public static DerbyConstraint create(Type type) {
        return new DerbyConstraint(type.name()
                                       .replace("_", " "));
    }

    public String toString() {
        return constraint;
    }

    public DerbyConstraint checkExpression(SqlExpression expression) {
        constraint += String.format(" (%s)", expression.render());
        return this;
    }

    public DerbyConstraint defaultExpression(SqlExpression expression) {
        constraint += String.format(" %s", expression.render());
        return this;
    }

    public DerbyConstraint withName(String name) {
        constraint = String.format("CONSTRAINT %s %s", name, constraint);
        return this;
    }

    public DerbyConstraint columns(String... columnNames) {
        constraint += String.format(" (%s)", String.join(", ", columnNames));
        return this;
    }

    // A method to define the specifics of a foreign key constraint
    public DerbyConstraint references(String referenceTable, String... referenceColumns) {
        constraint += String.format(" REFERENCES %s(%s)", referenceTable, String.join(", ", referenceColumns));
        return this;
    }

    public DerbyConstraint action(DerbyReferenceClause... actions) {
        constraint += " " + Arrays.stream(actions)
                                  .map(DerbyReferenceClause::getClause)
                                  .collect(Collectors.joining(" "));
        return this;
    }

    public DerbyConstraint startsWith(int num) {
        constraint += String.format(" (START WITH %d)", num);
        return this;
    }

    public DerbyConstraint startsWithIncrementBy(int start, int increment) {
        constraint += String.format("(STARTS WITH %d, INCREMENT BY %d)", start, increment);
        return this;
    }

    public enum Type {
        PRIMARY_KEY,
        UNIQUE,
        NOT_NULL,
        CHECK,
        FOREIGN_KEY,
        DEFAULT,
        WITH_DEFAULT,
        GENERATED_ALWAYS_AS_IDENTITY,
        GENERATED_BY_DEFAULT_AS_IDENTITY,
        GENERATED_ALWAYS_AS
    }
}
