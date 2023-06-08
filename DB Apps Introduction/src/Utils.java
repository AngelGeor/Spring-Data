import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Utils {
    ;
    static Connection getSQLConnection () throws SQLException {
        final Properties prop = new Properties();
        prop.setProperty(Constants.USER_NAME, Constants.USER_VALUE);
        prop.setProperty(Constants.PASSWORD_KEY, Constants.PASSWORD);

        return DriverManager.getConnection(Constants.JDBC_URL, prop);
    }
}
