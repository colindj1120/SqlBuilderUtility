package com.hyperion.sqlbuilder.sqlexpressions;

import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyConstraint;
import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType;

import java.util.Optional;

public class CastExpression implements SqlExpression {
    private final String        castObj;
    private final DerbyDataType castType;

    private CastExpression(String castObj, DerbyDataType castType) {
        this.castObj  = castObj;
        this.castType = castType;
    }

    public static CastExpression createNullCast(DerbyDataType castType) {
        return new CastExpression(null, castType);
    }

    public static CastExpression createCast(SqlExpression expression, DerbyDataType castType) {
        return new CastExpression(expression.render(), castType);
    }

    @Override
    public String render() {
        return String.format("%s AS %s", Optional.ofNullable(castObj)
                                                 .orElse("NULL"), castType.toString());
    }
}
