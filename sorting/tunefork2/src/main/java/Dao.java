//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

public abstract class Dao {

    protected Connection connection;

    public Dao(Connection connection) {
        this.connection = connection;
    }

    public void startConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException var4) {
                    var4.printStackTrace();
                }

                SQLiteConfig config = new SQLiteConfig();
                config.enforceForeignKeys(true);

                try {
                    this.connection = DriverManager.getConnection("jdbc:sqlite:Table1.db", config.toProperties());

                } catch (SQLException var3) {
                    var3.printStackTrace();
                }
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (Exception var2) {
            System.out.println("could not close connection");
        }

    }

    public Connection getConnection() {
        return connection;
    }
}
