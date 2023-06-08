import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNameCasing {
    private static final String UPDATE_TOWN_NAMES =
            "UPDATE minions_db.towns as t set name = upper(name) where t.country = ?";
    private static final String GET_TOWN_NAMES_BY_COUNTRY =
            "SELECT t.name from minions_db.towns as t where t.country = ?";
    private static final String NO_AFFECTED_TOWNS_MESSAGE = "No town names were affected.";
    private static final String AFFECTED_TOWNS_MESSAGE = "%d town names were affected.%n";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();
        Scanner scanner = new Scanner(System.in);
        String townName = scanner.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOWN_NAMES);
        preparedStatement.setString(1,townName);

        int updated_count = preparedStatement.executeUpdate();

        if(updated_count == 0) {
            System.out.println(NO_AFFECTED_TOWNS_MESSAGE);
            return;
        }
        System.out.printf(AFFECTED_TOWNS_MESSAGE,updated_count);
        PreparedStatement selectAllTowns = connection.prepareStatement(GET_TOWN_NAMES_BY_COUNTRY);
        selectAllTowns.setString(1,townName);
         ResultSet resSet = selectAllTowns.executeQuery();

        List<String> towns = new ArrayList<>();

        while(resSet.next()) {

            towns.add(resSet.getString("name"));
        }
        System.out.println(towns);
    }
}
