package com.hyperion.sqlbuilder.datatypes.Join;

@SuppressWarnings("unused")
public interface Join extends JoinType {
    Join JOIN             = () -> "JOIN";
    Join INNER_JOIN       = () -> "INNER JOIN";
    Join LEFT_JOIN        = () -> "LEFT JOIN";
    Join LEFT_OUTER_JOIN  = () -> "LEFT OUTER JOIN";
    Join RIGHT_JOIN       = () -> "RIGHT JOIN";
    Join RIGHT_OUTER_JOIN = () -> "RIGHT OUTER JOIN";
    Join CROSS_JOIN       = () -> "CROSS JOIN";
}
