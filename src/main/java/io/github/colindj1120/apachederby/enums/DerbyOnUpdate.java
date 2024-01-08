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
package io.github.colindj1120.apachederby.enums;

import io.github.colindj1120.apachederby.interfaces.DerbyReferenceClause;

public enum DerbyOnUpdate implements DerbyReferenceClause {
    ON_UPDATE_NO_ACTION("NO ACTION"),
    ON_UPDATE_RESTRICT("RESTRICT");

    private final String action;

    DerbyOnUpdate(String action) {
        this.action = action;
    }

    @Override
    public String getClause() {
        return "ON UPDATE %s".formatted(action);
    }
}
