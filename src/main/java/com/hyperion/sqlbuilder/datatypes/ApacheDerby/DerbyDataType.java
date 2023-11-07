package com.hyperion.sqlbuilder.datatypes.ApacheDerby;

public class DerbyDataType {
    public static DerbyDataType dataType(DataType type) {
        return new DerbyDataType(type.name().replace("_", " "));
    }

    public static DerbyDataType dataTypeWithLength(DataType type, int length) {
        return new DerbyDataType(type.name().replace("_", " "), length);
    }

    public static DerbyDataType dataTypeWithPrecisionAndScale(DataType type, int precision, int scale) {
        return new DerbyDataType(type.name().replace("_", " "), precision, scale);
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

    @Override
    public String toString() {
        return dataType;
    }
}

