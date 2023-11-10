package com.hyperion.sqlbuilder.sqlexpressions.ApacheDerby;

import com.hyperion.sqlbuilder.sqlexpressions.Query;
import com.hyperion.sqlbuilder.sqlexpressions.SqlExpression;
import com.hyperion.sqlbuilder.sqlexpressions.Values;

@SuppressWarnings("unused")
public class DerbyIntersect extends SqlExpression<DerbyIntersect> {
    private final StringBuilder intersectBuilder = new StringBuilder();

    private DerbyIntersect(String starting) {
        intersectBuilder.append(starting);
    }

    public static DerbyIntersect queryStart(Query query) {
        return new DerbyIntersect(query.render());
    }

    public DerbyIntersect valuesStart(Values values) {
        return new DerbyIntersect(values.render());
    }

    public DerbyIntersect values(Values values) {
        return append(values);
    }

    public DerbyIntersect query(Query query) {
        return append(query);
    }

    public DerbyIntersect intersect() {
        return append("INTERSECT");
    }

    public DerbyIntersect intersectAll() {
        return append("INTERSECT ALL");
    }

    public DerbyIntersect intersectDistinct() {
        return append("INTERSECT DISTINCT");
    }

    private DerbyIntersect append(SqlExpression<?> values) {
        intersectBuilder.append("\n").append(values.render());
        return this;
    }

    private DerbyIntersect append(String intersectType) {
        intersectBuilder.append("\n").append(intersectType);
        return this;
    }

    @Override
    public String render() {
        if (intersectBuilder.isEmpty()) {
            throw new IllegalStateException("No queries added to INTERSECT operation.");
        }

        return intersectBuilder.toString();
    }

    @Override
    public DerbyIntersect self() {
        return this;
    }
}
