/*
 * Copyright (C) 2024 Colin Jokisch
 * This file is part of SqlBuilderUtility (https://github.com/colindj1120/SqlBuilderUtility).
 *
 * SqlBuilderUtility is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SqlBuilderUtility is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SqlBuilderUtility.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.colindj1120.mysql.interfaces;

import io.github.colindj1120.sqlbase.interfaces.Join;

public interface MySqlJoin extends Join {
    MySqlJoin NATURAL_JOIN             = () -> "NATURAL JOIN";
    MySqlJoin NATURAL_INNER_JOIN       = () -> "NATURAL INNER JOIN";
    MySqlJoin NATURAL_LEFT_JOIN        = () -> "NATURAL LEFT JOIN";
    MySqlJoin NATURAL_LEFT_OUTER_JOIN  = () -> "NATURAL LEFT OUTER JOIN";
    MySqlJoin NATURAL_RIGHT_JOIN       = () -> "NATURAL RIGHT JOIN";
    MySqlJoin NATURAL_RIGHT_OUTER_JOIN = () -> "NATURAL RIGHT OUTER JOIN";
    MySqlJoin STRAIGHT_JOIN            = () -> "STRAIGHT JOIN";
    MySqlJoin SELF_JOIN                = () -> "SELF JOIN";
}
