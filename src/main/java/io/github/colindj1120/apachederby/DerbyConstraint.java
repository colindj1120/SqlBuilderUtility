/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of SqlBuilderUtility (https://github.com/colindj1120/SqlBuilderUtility).
 *
 * SqlBuilderUtility is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SqlBuilderUtility is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SqlBuilderUtility.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.apachederby;

import io.github.colindj1120.apachederby.interfaces.DerbyReferenceClause;
import io.github.colindj1120.sqlbase.expressions.Column;
import io.github.colindj1120.sqlbase.expressions.SqlExpression;
import io.github.colindj1120.sqlbase.expressions.Table;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DerbyConstraint {
    private String constraint;

    private DerbyConstraint(String constraint) {
        this.constraint = constraint;
    }

    // Factory method for creating a new DerbyConstraint instance
    public static DerbyConstraint constraint(Type type) {
        return new DerbyConstraint(type.name()
                                       .replace("_", " "));
    }

    public static DerbyConstraint constraints(Type... types) {
        String constraint = Arrays.stream(types)
                                  .map(type -> type.name()
                                                   .replace("_", " "))
                                  .collect(Collectors.joining(" "));
        return new DerbyConstraint(constraint);
    }

    public String toString() {
        return constraint;
    }

    public DerbyConstraint checkExpression(SqlExpression<?> expression) {
        constraint += String.format(" (%s)", expression.render());
        return this;
    }

    public DerbyConstraint defaultExpression(SqlExpression<?> expression) {
        constraint += String.format(" %s", expression.render());
        return this;
    }

    public DerbyConstraint withName(String name) {
        constraint = String.format("CONSTRAINT %s %s", name, constraint);
        return this;
    }

    public DerbyConstraint columns(Column... columns) {
        constraint += String.format(" (%s)", Arrays.stream(columns)
                                                   .map(Column::nameOnly)
                                                   .map(Column::render)
                                                   .collect(Collectors.joining(", ")));
        return this;
    }

    // A method to define the specifics of a foreign key constraint
    public DerbyConstraint references(Table referenceTable, Column... referenceColumns) {
        constraint += String.format(" REFERENCES %s(%s)", referenceTable.nameOnly(), Arrays.stream(referenceColumns)
                                                                                           .map(Column::nameOnly)
                                                                                           .map(Column::render)
                                                                                           .collect(Collectors.joining(", '")));
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
