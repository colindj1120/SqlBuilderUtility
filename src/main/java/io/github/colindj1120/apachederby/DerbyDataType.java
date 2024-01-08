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
package io.github.colindj1120.apachederby;

public class DerbyDataType {
    private final String dataType;

    private DerbyDataType(String dataType) {
        this.dataType = dataType;
    }

    private DerbyDataType(String dataType, int length) {
        this.dataType = String.format("%s(%d)", dataType, length);
    }

    private DerbyDataType(String dataType, int precision, int scale) {
        this.dataType = String.format("%s(%d, %d)", dataType, precision, scale);
    }

    public static DerbyDataType dataType(DataType type) {
        return new DerbyDataType(type.name()
                                     .replace("_", " "));
    }

    public static DerbyDataType dataTypeWithLength(DataType type, int length) {
        return new DerbyDataType(type.name()
                                     .replace("_", " "), length);
    }

    public static DerbyDataType dataTypeWithPrecisionAndScale(DataType type, int precision, int scale) {
        return new DerbyDataType(type.name()
                                     .replace("_", " "), precision, scale);
    }

    @Override
    public String toString() {
        return dataType;
    }

    public enum DataType {
        // Numeric types
        INTEGER,
        INT, // Alias for INTEGER
        SMALLINT,
        BIGINT,
        DECIMAL,
        NUMERIC,
        FLOAT,
        REAL,
        DOUBLE,

        // Character types
        CHAR,
        VARCHAR,
        LONG_VARCHAR,
        CLOB,

        // Unicode character types
        CHAR_FOR_BIT_DATA,
        VARCHAR_FOR_BIT_DATA,
        LONG_VARCHAR_FOR_BIT_DATA,

        // Bit types
        BIT,
        BIT_VARYING,

        // Date and Time types
        DATE,
        TIME,
        TIMESTAMP,

        // Binary types
        BINARY,
        VARBINARY,
        LONG_VARBINARY,
        BLOB,

        // Others
        BOOLEAN,
        XML
    }
}

