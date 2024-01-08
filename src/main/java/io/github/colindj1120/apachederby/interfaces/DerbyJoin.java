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
package io.github.colindj1120.apachederby.interfaces;

import io.github.colindj1120.sqlbase.interfaces.Join;

public interface DerbyJoin extends Join {
    DerbyJoin NATURAL_JOIN             = () -> "NATURAL JOIN";
    DerbyJoin NATURAL_INNER_JOIN       = () -> "NATURAL INNER JOIN";
    DerbyJoin NATURAL_LEFT_JOIN        = () -> "NATURAL LEFT JOIN";
    DerbyJoin NATURAL_LEFT_OUTER_JOIN  = () -> "NATURAL LEFT OUTER JOIN";
    DerbyJoin NATURAL_RIGHT_JOIN       = () -> "NATURAL RIGHT JOIN";
    DerbyJoin NATURAL_RIGHT_OUTER_JOIN = () -> "NATURAL RIGHT OUTER JOIN";
}
