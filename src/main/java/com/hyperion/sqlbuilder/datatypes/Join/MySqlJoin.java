package com.hyperion.sqlbuilder.datatypes.Join;

public interface MySqlJoin extends Join {
    MySqlJoin NATURAL_JOIN = () -> "NATURAL JOIN";
    MySqlJoin NATURAL_INNER_JOIN = () -> "NATURAL INNER JOIN";
    MySqlJoin NATURAL_LEFT_JOIN = () -> "NATURAL LEFT JOIN";
    MySqlJoin NATURAL_LEFT_OUTER_JOIN = () -> "NATURAL LEFT OUTER JOIN";
    MySqlJoin NATURAL_RIGHT_JOIN = () -> "NATURAL RIGHT JOIN";
    MySqlJoin NATURAL_RIGHT_OUTER_JOIN = () -> "NATURAL RIGHT OUTER JOIN";
    MySqlJoin STRAIGHT_JOIN = () -> "STRAIGHT JOIN";
    MySqlJoin SELF_JOIN = () -> "SELF JOIN";
}
