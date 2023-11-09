package com.hyperion.sqlbuilder.datatypes.Join;

@SuppressWarnings("unused")
public interface DerbyJoin extends Join {
    DerbyJoin NATURAL_JOIN = () -> "NATURAL JOIN";
    DerbyJoin NATURAL_INNER_JOIN = () -> "NATURAL INNER JOIN";
    DerbyJoin NATURAL_LEFT_JOIN = () -> "NATURAL LEFT JOIN";
    DerbyJoin NATURAL_LEFT_OUTER_JOIN = () -> "NATURAL LEFT OUTER JOIN";
    DerbyJoin NATURAL_RIGHT_JOIN = () -> "NATURAL RIGHT JOIN";
    DerbyJoin NATURAL_RIGHT_OUTER_JOIN = () -> "NATURAL RIGHT OUTER JOIN";
}
