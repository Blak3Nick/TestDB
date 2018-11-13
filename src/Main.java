import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:testjava.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                                "(name TEXT, phone INTEGER, email TEXT)");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Blake', 6452323, 'email.com' )");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Jane', 6452323, 'email.com' )");
//            statement.execute("INSERT INTO contacts (name, phone, email)" +
//                    "VALUES ('Jill', 6452323, 'email.com' )");
//            statement.execute("UPDATE contacts SET phone=55555555 WHERE name='Jane'" );
//            statement.execute("DELETE FROM contacts WHERE name='Jill'");

            statement.execute("SELECT * FROM contacts");
            ResultSet results = statement.getResultSet();
            while (results.next()) {
                System.out.println( results.getString("name") + " "+
                                    results.getInt("phone") + " " +
                                    results.getString("email"));
            }
            results.close();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("something went wrong " + e.getMessage());
        }
    }
}
