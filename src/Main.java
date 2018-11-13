import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:testjava.db";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:testjava.db");
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                    " (" + COLUMN_NAME + " text, " +
                                            COLUMN_PHONE + " integer, " +
                                            COLUMN_EMAIL + " text" +
                                    ")");

            insertContact(statement, "Blake", 6452323, "someemail.com");
            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                                "(" + COLUMN_NAME + ", " +
                                        COLUMN_PHONE + ", " +
                                        COLUMN_EMAIL +
                                ")" +
                                "VALUES('Tim', 6456458, 'tim@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", " +
                    COLUMN_PHONE + ", " +
                    COLUMN_EMAIL +
                    ")" +
                    "VALUES('Bill', 1234567, 'bill@email.com')");
            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", " +
                    COLUMN_PHONE + ", " +
                    COLUMN_EMAIL +
                    ")" +
                    "VALUES('Jill', 4568797, 'jill@email.com')");
            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                    COLUMN_PHONE + "=77777777" +
                    " WHERE " + COLUMN_NAME + "='Tim'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "='Tim'");

//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Blake', 6452323, 'email.com' )");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Jane', 6452323, 'email.com' )");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Jill', 6452323, 'email.com' )");
//            statement.execute("UPDATE contacts SET phone=55555555 WHERE name='Jane'" );
//            statement.execute("DELETE FROM contacts WHERE name='Jill'");

//            statement.execute("SELECT * FROM contacts");
//            ResultSet results = statement.getResultSet();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (results.next()) {
                System.out.println( results.getString(COLUMN_NAME) + " "+
                                    results.getInt(COLUMN_PHONE) + " " +
                                    results.getString(COLUMN_EMAIL));
            }
            results.close();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                "(" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ")" +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}
