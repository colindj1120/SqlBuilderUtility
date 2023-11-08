package com.hyperion.sqlbuilder.builders;

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

