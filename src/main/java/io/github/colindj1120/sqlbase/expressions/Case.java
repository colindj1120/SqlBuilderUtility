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

public class Case extends SqlExpression<Case> {
    private final StringBuilder expression = new StringBuilder();

    private Case() {}

    public static Case beginCase() {
        return new Case();
    }

    public Case whenThen(SqlExpression<?> when, SqlExpression<?> then) {
        this.expression.append(" WHEN ")
                       .append(when.render())
                       .append(" THEN ")
                       .append(then.render());
        return self();
    }

    public Case elseCase(SqlExpression<?> elseCase) {
        this.expression.append(" ELSE ")
                       .append(elseCase.render());
        return self();
    }

    @Override
    public String render() {
        expression.append(" ");
        return expression.toString();
    }

    @Override
    public Case self() {
        return this;
    }
}
