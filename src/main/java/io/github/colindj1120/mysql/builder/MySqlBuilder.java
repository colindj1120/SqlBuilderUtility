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
package io.github.colindj1120.mysql.builder;

import io.github.colindj1120.sqlbase.builder.SqlBuilder;

/**
 * Concrete implementation of SqlBuilder for MySQL. This class extends the generic SqlBuilder to provide MySQL-specific SQL building capabilities.
 *
 * @author Colin Jokisch
 * @version 1.0
 */
public class MySqlBuilder extends SqlBuilder<MySqlBuilder> {

    /**
     * Constructs a new MySQLBuilder instance.
     */
    public MySqlBuilder() {
        super();
    }

    /**
     * Method used internally to cast the current instance to the MySQLBuilder type.
     *
     * @return the current instance cast to MySQLBuilder
     */
    @Override
    protected MySqlBuilder self() {
        return this;
    }

    /**
     * Appends a LIMIT clause to the SQL query.
     *
     * @param limit
     *         the maximum number of rows to return
     *
     * @return the current instance of the SQL builder for method chaining
     */
    public MySqlBuilder limit(int limit) {
        builder.append(" LIMIT ")
               .append(limit);
        return this;
    }

    // Other MySQL-specific methods can be added here
}

// Usage example:
// MySQLBuilder mySQLBuilder = new MySQLBuilder();
// String query = mySQLBuilder.select("id", "name")
//                            .from("users")
//                            .where("id > 10")
//                            .orderBy("name")
//                            .limit(10)
//                            .build();
// System.out.println(query);

