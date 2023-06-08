import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {

    private static final String GET_PROCEDURE = "{CALL usp_get_older (?)}";
    private static final String FIND_MINIONS_AGE_AND_NAME =
            "SELECT m.name, m.age FROM minions_db.minions AS m WHERE id = ?";
    private static final String PRINT_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
       int id = Integer.parseInt(scanner.nextLine());

        final Connection connection = Utils.getSQLConnection();

        final CallableStatement getOlderStoredProcedure = connection.prepareCall(GET_PROCEDURE);
        getOlderStoredProcedure.setInt(1, id);
        getOlderStoredProcedure.execute();

        final PreparedStatement minionsStatement = connection.prepareStatement(FIND_MINIONS_AGE_AND_NAME);
        minionsStatement.setInt(1, id);

        final ResultSet minions = minionsStatement.executeQuery();
        minions.next();

        System.out.printf(PRINT_FORMAT,
                minions.getString("name"),
                minions.getInt("age"));


    }
}

