//package com.hyperion.sqlbuilder;
//
//import com.hyperion.sqlbuilder.builders.DerbyBuilder;
//import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyConstraint;
//import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType;
//import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyOnDelete;
//import com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyOnUpdate;
//import org.junit.jupiter.api.Test;
//
//import static com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyConstraint.Type.*;
//import static com.hyperion.sqlbuilder.datatypes.ApacheDerby.DerbyDataType.DataType.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DerbyBuilderCreateTableTest {
//
//    @Test
//    public void testCreateTableWithCompositePrimaryKey() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("EmployeeTable")
//               .addColumn("id", DerbyDataType.dataType(BIGINT))
//               .addColumn("department_id", DerbyDataType.dataType(BIGINT))
//               .addConstraint(DerbyConstraint.create(PRIMARY_KEY).columns("id", "department_id"))
//               .endTable();
//
//        String expectedSql = "CREATE TABLE EmployeeTable (\n" +
//                             "\tid BIGINT,\n" +
//                             "\tdepartment_id BIGINT,\n" +
//                             "\tPRIMARY KEY (id, department_id)\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testCreateTableWithCompositeForeignKey() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("OrdersTable")
//               .addColumn("order_id", DerbyDataType.dataType(INT))
//               .addColumn("product_id", DerbyDataType.dataType(INT))
//               .addColumn("customer_id", DerbyDataType.dataType(INT))
//               .addConstraint(DerbyConstraint.create(FOREIGN_KEY).columns("product_id", "customer_id")
//                                             .references("CustomerProductsTable", "product_id", "customer_id")
//                                             .action(DerbyOnUpdate.NO_ACTION, DerbyOnDelete.CASCADE))
//               .endTable();
//
//        String expectedSql = "CREATE TABLE OrdersTable (\n" +
//                             "\torder_id INT,\n" +
//                             "\tproduct_id INT,\n" +
//                             "\tcustomer_id INT,\n" +
//                             "\tFOREIGN KEY (product_id, customer_id) REFERENCES CustomerProductsTable(product_id, customer_id) ON UPDATE NO ACTION ON DELETE CASCADE\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithVarcharPrimaryKey() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("UserAccount")
//               .addColumn("username", DerbyDataType.dataTypeWithLength(VARCHAR,50), DerbyConstraint.create(PRIMARY_KEY))
//               .endTable();
//        String expectedSql = "CREATE TABLE UserAccount (\n" +
//                             "\tusername VARCHAR(50) PRIMARY KEY\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithCheckConstraint() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("ProductInventory")
//               .addColumn("product_id", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("quantity", DerbyDataType.dataType(INT))
//               .addConstraint(DerbyConstraint.create(CHECK).checkExpression(() -> "quantity >= 0")) // Placeholder for actual SQLExpression
//               .endTable();
//        String expectedSql = "CREATE TABLE ProductInventory (\n" +
//                             "\tproduct_id INT PRIMARY KEY,\n" +
//                             "\tquantity INT,\n" +
//                             "\tCHECK (quantity >= 0)\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithDefaultValues() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("CustomerOrder")
//               .addColumn("order_id", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("orderDate", DerbyDataType.dataType(DATE))
//               .addColumn("status", DerbyDataType.dataTypeWithLength(VARCHAR, 20), DerbyConstraint.create(DEFAULT).defaultExpression(() -> "'PENDING'"))
//               .endTable();
//        String expectedSql = "CREATE TABLE CustomerOrder (\n" +
//                             "\torder_id INT PRIMARY KEY,\n" +
//                             "\torderDate DATE,\n" +
//                             "\tstatus VARCHAR(20) DEFAULT 'PENDING'\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithCompositeKey() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("Employee")
//               .addColumn("employeeId", DerbyDataType.dataType(INT))
//               .addColumn("departmentId", DerbyDataType.dataType(INT))
//               .addConstraint(DerbyConstraint.create(PRIMARY_KEY).columns("employeeId", "departmentId"))
//               .endTable();
//        String expectedSql = "CREATE TABLE Employee (\n" +
//                             "\temployeeId INT,\n" +
//                             "\tdepartmentId INT,\n" +
//                             "\tPRIMARY KEY (employeeId, departmentId)\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithUniqueConstraint() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("Supplier")
//               .addColumn("supplierId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("email", DerbyDataType.dataTypeWithLength(VARCHAR, 255), DerbyConstraint.create(UNIQUE))
//               .endTable();
//        String expectedSql = "CREATE TABLE Supplier (\n" +
//                             "\tsupplierId INT PRIMARY KEY,\n" +
//                             "\temail VARCHAR(255) UNIQUE\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithForeignKeyAndCascadeDelete() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("Book")
//               .addColumn("bookId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("authorId", DerbyDataType.dataType(INT))
//                .addConstraint(DerbyConstraint.create(FOREIGN_KEY).columns("authorId").references("Author", "authorId").action(DerbyOnDelete.CASCADE))
//               .endTable();
//        String expectedSql = "CREATE TABLE Book (\n" +
//                             "\tbookId INT PRIMARY KEY,\n" +
//                             "\tauthorId INT,\n" +
//                             "\tFOREIGN KEY (authorId) REFERENCES Author(authorId) ON DELETE CASCADE\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithTimestampAndNotNullConstraint() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("Movie")
//               .addColumn("movieId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("releaseDate", DerbyDataType.dataType(TIMESTAMP), DerbyConstraint.create(NOT_NULL))
//               .endTable();
//        String expectedSql = "CREATE TABLE Movie (\n" +
//                             "\tmovieId INT PRIMARY KEY,\n" +
//                             "\treleaseDate TIMESTAMP NOT NULL\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithAutoIncrementPrimaryKey() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("EventLog")
//               .addColumn("logId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY), DerbyConstraint.create(GENERATED_ALWAYS_AS_IDENTITY))
//               .addColumn("event", DerbyDataType.dataTypeWithLength(VARCHAR, 255))
//               .endTable();
//        String expectedSql = "CREATE TABLE EventLog (\n" +
//                             "\tlogId INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,\n" +
//                             "\tevent VARCHAR(255)\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithEnumTypeUsingCheckConstraint() {
//        // Derby doesn't support ENUM, so we simulate it with a CHECK constraint
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("UserRole")
//               .addColumn("roleId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("roleName", DerbyDataType.dataTypeWithLength(VARCHAR, 50), DerbyConstraint.create(CHECK).checkExpression(() -> "roleName IN ('ADMIN', 'USER', 'GUEST')"))
//               .endTable();
//        String expectedSql = "CREATE TABLE UserRole (\n" +
//                             "\troleId INT PRIMARY KEY,\n" +
//                             "\troleName VARCHAR(50) CHECK (roleName IN ('ADMIN', 'USER', 'GUEST'))\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithDecimalTypeAndCheckConstraint() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("Transaction")
//               .addColumn("transactionId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("amount", DerbyDataType.dataTypeWithPrecisionAndScale(DECIMAL, 10, 2), DerbyConstraint.create(CHECK).checkExpression(() -> "amount >= 0"))
//               .endTable();
//        String expectedSql = "CREATE TABLE Transaction (\n" +
//                             "\ttransactionId INT PRIMARY KEY,\n" +
//                             "\tamount DECIMAL(10, 2) CHECK (amount >= 0)\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//
//    @Test
//    public void testTableWithBlobType() {
//        DerbyBuilder builder = new DerbyBuilder();
//        builder.createTable("UserProfile")
//               .addColumn("userId", DerbyDataType.dataType(INT), DerbyConstraint.create(PRIMARY_KEY))
//               .addColumn("profilePicture", DerbyDataType.dataType(BLOB))
//               .endTable();
//        String expectedSql = "CREATE TABLE UserProfile (\n" +
//                             "\tuserId INT PRIMARY KEY,\n" +
//                             "\tprofilePicture BLOB\n)";
//        assertEquals(expectedSql, builder.build());
//    }
//}