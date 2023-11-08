package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType;

import java.util.Optional;

public class Cast extends SqlExpression<Cast> {
    private Cast(String castObj, DerbyDataType castType) {
        super();

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
    protected Cast self() {
        return this;
    }
}
