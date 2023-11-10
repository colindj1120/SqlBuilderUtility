package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.Query;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;
import com.hyperion.sqlbuilder.sqlexpressions.Values;

@SuppressWarnings("unused")
public class DerbyExcept extends SqlExpression<DerbyExcept> {
    private final StringBuilder exceptBuilder = new StringBuilder();

    private DerbyExcept(String starting) {
        exceptBuilder.append(starting);
    }

    public static DerbyExcept queryStart(Query query) {
        return new DerbyExcept(query.render());
    }

    public DerbyExcept valuesStart(Values values) {
        return new DerbyExcept(values.render());
    }

    public DerbyExcept values(Values values) {
        return append(values);
    }

    public DerbyExcept query(Query query) {
        return append(query);
    }

    public DerbyExcept except() {
        return append("EXCEPT");
    }

    public DerbyExcept exceptAll() {
        return append("EXCEPT ALL");
    }

    public DerbyExcept exceptDistinct() {
        return append("EXCEPT DISTINCT");
    }

    private DerbyExcept append(SqlExpression<?> values) {
        exceptBuilder.append("\n").append(values.render());
        return this;
    }

    private DerbyExcept append(String exceptType) {
        exceptBuilder.append("\n").append(exceptType);
        return this;
    }

    @Override
    public String render() {
        if (exceptBuilder.isEmpty()) {
            throw new IllegalStateException("No queries added to EXCEPT operation.");
        }

        return exceptBuilder.toString();
    }

    @Override
    public DerbyExcept self() {
        return this;
    }
}
