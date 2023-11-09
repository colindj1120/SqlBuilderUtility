package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType;

import java.util.Optional;

public class Cast extends SqlExpression<Cast> {
    private final StringBuilder expression = new StringBuilder();

    private Cast(String castObj, DerbyDataType castType) {
        this.expression.append(Optional.ofNullable(castObj)
                                       .orElse("NULL"))
                       .append(" AS ")
                       .append(castType.toString());
    }

    public static Cast createNullCast(DerbyDataType castType) {
        return new Cast(null, castType);
    }

    public static Cast createCast(SqlExpression<?> expression, DerbyDataType castType) {
        return new Cast(expression.render(), castType);
    }

    @Override
    public String render() {
        return expression.toString();
    }

    @Override
    public Cast self() {
        return null;
    }
}
