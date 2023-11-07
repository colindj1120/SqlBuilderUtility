package com.hyperion.sqlbuilder.builders;

/**
 * Concrete implementation of SqlBuilder for MySQL.
 * This class extends the generic SqlBuilder to provide MySQL-specific SQL building capabilities.
 *
 * @version 1.0
 * @author Colin Jokisch
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
     * @param limit the maximum number of rows to return
     * @return the current instance of the SQL builder for method chaining
     */
    public MySqlBuilder limit(int limit) {
        builder.append(" LIMIT ").append(limit);
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

