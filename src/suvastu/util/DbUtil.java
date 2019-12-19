package suvastu.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                try {

                    Properties prop = new Properties();
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    InputStream stream = loader.getResourceAsStream("config.properties");
                    prop.load(stream);

                    String driver = prop.getProperty("driver");
                    String url = prop.getProperty("url");
                    String user = prop.getProperty("user");
                    String password = prop.getProperty("password");

                    Class.forName(driver);
                    connection = DriverManager.getConnection(url, user, password);

                } catch (ClassNotFoundException | SQLException | IOException e) {
                    System.out.println("Cannot Establish Database Connection.");
                }

            } else {
                return connection;
            }
        } catch (SQLException ex) {
            System.out.println("Error..:DbUtil:getConnection:"+ex);
        }

        return connection;
    }


}
