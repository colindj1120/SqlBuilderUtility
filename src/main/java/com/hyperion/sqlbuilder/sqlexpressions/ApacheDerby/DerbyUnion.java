package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.Query;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;
import com.hyperion.sqlbuilder.sqlexpressions.Values;

@SuppressWarnings("unused")
public class DerbyUnion extends SqlExpression<DerbyUnion> {
    private final StringBuilder unionBuilder = new StringBuilder();

    private DerbyUnion(String starting) {
        unionBuilder.append(starting);
    }

    public static DerbyUnion queryStart(Query query) {
        return new DerbyUnion(query.render());
    }

    public DerbyUnion valuesStart(Values values) {
        return new DerbyUnion(values.render());
    }

    public DerbyUnion query(Query query) {
        return append(query);
    }

    public DerbyUnion values(Values values) {
        return append(values);
    }

    public DerbyUnion union() {
        return append("UNION");
    }

    public DerbyUnion unionAll() {
        return append("UNION ALL");
    }

    public DerbyUnion unionDistinct() {
        return append("UNION DISTINCT");
    }

    private DerbyUnion append(SqlExpression<?> values) {
        unionBuilder.append("\n").append(values.render());
        return this;
    }

    private DerbyUnion append(String unionType) {
        unionBuilder.append("\n").append(unionType);
        return this;
    }

    /**
     * Renders the SQL UNION expression.
     *
     * @return The rendered SQL UNION string.
     */
    @Override
    public String render() {
        if (unionBuilder.isEmpty()) {
            throw new IllegalStateException("No queries added to UNION operation.");
        }

        return unionBuilder.toString();
    }

    @Override
    public DerbyUnion self() {
        return this;
    }
}
