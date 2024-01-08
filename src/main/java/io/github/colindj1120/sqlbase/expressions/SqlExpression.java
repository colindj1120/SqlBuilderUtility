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

public abstract class SqlExpression<T extends SqlExpression<T>> {
    public abstract String render();

    public T alias(String alias) {
        throw new UnsupportedOperationException(String.format("Alias is not supported by %s", self().getClass()
                                                                                                    .getSimpleName()));
    }

    public T correlation(String correlation) {
        throw new UnsupportedOperationException(String.format("Correlation is not supported by %s", self().getClass()
                                                                                                          .getSimpleName()));
    }

    public T reference(String reference) {
        throw new UnsupportedOperationException(String.format("Reference is not supported by %s", self().getClass()
                                                                                                        .getSimpleName()));
    }

    public abstract T self();
}


