package com.hyperion.sqlbuilder.builders;

import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyConstraint;
import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType;

/**
 * Concrete implementation of SqlBuilder for Apache Derby.
 * This class extends the generic SqlBuilder to provide Derby-specific SQL building capabilities.
 *
 * @version 1.0
 * @author Colin Jokisch
 */
public class DerbyBuilder extends SqlBuilder<DerbyBuilder> {

    /**
     * Constructs a new DerbyBuilder instance.
     */
    public DerbyBuilder() {
        super();
    }

    /**
     * Method used internally to cast the current instance to the DerbyBuilder type.
     *
     * @return the current instance cast to DerbyBuilder
     */
    @Override
    protected DerbyBuilder self() {
        return this;
    }

    public DerbyBuilder addColumn(String name, DerbyDataType type, DerbyConstraint... constraints) {
        if (builder.lastIndexOf("(\n") != builder.length() - 2 && builder.lastIndexOf("(\n") != -1) {
            builder.append(",\n");
        } else {
            builder.append(" (\n");
        }
        builder.append("\t").append(name).append(" ").append(type.toString());

        for (DerbyConstraint constraint : constraints) {
            builder.append(" ").append(constraint.toString());
        }
        return this;
    }

    public DerbyBuilder addConstraint(DerbyConstraint constraint) {
        builder.append(",\n\t").append(constraint.toString());
        return this;
    }

    /**
     * Appends a NEXT VALUE FOR clause for a sequence in an SQL statement.
     *
     * @param sequenceName the name of the sequence
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder nextValueFor(String sequenceName) {
        builder.append("NEXT VALUE FOR ").append(sequenceName);
        return this;
    }

    /**
     * Appends a FOR UPDATE clause to the SQL query, optionally specifying columns.
     *
     * @param columns the columns to be updated, varargs to allow specifying multiple columns
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forUpdate(String... columns) {
        builder.append(" FOR UPDATE");
        if (columns.length > 0) {
            builder.append(" OF ").append(String.join(", ", columns));
        }
        return this;
    }

    /**
     * Appends a FOR READ ONLY clause to the SQL query.
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forReadOnly() {
        builder.append(" FOR READ ONLY");
        return this;
    }

    /**
     * Appends a FOR FETCH ONLY clause to the SQL query.
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public DerbyBuilder forFetchOnly() {
        builder.append(" FOR FETCH ONLY");
        return this;
    }
}

