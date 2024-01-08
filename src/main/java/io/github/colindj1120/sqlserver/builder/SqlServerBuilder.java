package io.github.colindj1120.sqlserver.builder;/*
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

import io.github.colindj1120.sqlbase.builder.SqlBuilder;

public class SqlServerBuilder extends SqlBuilder<SqlServerBuilder> {

    public SqlServerBuilder fetchWithTies(int rowCount) {
        builder.append(" FETCH NEXT ")
               .append(rowCount)
               .append(rowCount == 1 ? " ROW" : " ROWS")
               .append(" WITH TIES");
        return this;
    }

    public SqlServerBuilder nextValueFor(String sequenceName) {
        builder.append("NEXT VALUE FOR ")
               .append(sequenceName);
        return this;
    }

    // Override self() to return this instance
    @Override
    protected SqlServerBuilder self() {
        return this;
    }
}

