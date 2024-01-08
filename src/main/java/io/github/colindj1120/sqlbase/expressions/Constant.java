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

public class Constant extends SqlExpression<Constant> {
    private final String constant;

    private Constant(String constant) {
        this.constant = constant;
    }

    public static Constant strConst(String constant) {
        return new Constant(String.format("'%s'", constant));
    }

    public static Constant numExprConst(String numericalExpression) {
        return new Constant(numericalExpression);
    }

    public static Constant numConst(Number number) {
        return new Constant(number.toString());
    }

    public static Constant boolConst(boolean bool) {
        return new Constant(bool ? "TRUE" : "FALSE");
    }

    @Override
    public String render() {
        return constant;
    }

    @Override
    public Constant self() {
        return this;
    }
}
