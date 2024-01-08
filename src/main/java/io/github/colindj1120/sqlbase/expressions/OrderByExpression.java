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
package io.github.colindj1120.sqlbase.expressions;

import io.github.colindj1120.sqlbase.enums.SortOrder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderByExpression extends SqlExpression<OrderByExpression> {
    private final List<Column> columns;
    private final SortOrder    sortOrder;

    private OrderByExpression(List<Column> columns, SortOrder sortOrder) {
        this.columns   = columns;
        this.sortOrder = sortOrder;
    }

    public static OrderByExpression asc(Column... columns) {
        return new OrderByExpression(Arrays.asList(columns), SortOrder.ASC);
    }

    public static OrderByExpression desc(Column... columns) {
        return new OrderByExpression(Arrays.asList(columns), SortOrder.DESC);
    }

    public static OrderByExpression column(Column... columns) {
        return new OrderByExpression(Arrays.asList(columns), SortOrder.NONE);
    }

    @Override
    public String render() {
        return columns.stream()
                      .map(column -> {
                          String name = column.hasAlias() ? column.getAlias() : column.render();
                          return String.format("%s%s", name, sortOrder == SortOrder.NONE ? "" : String.format(" %s", sortOrder.name()));
                      })
                      .collect(Collectors.joining(", "));
    }

    @Override
    public OrderByExpression self() {
        return this;
    }
}


