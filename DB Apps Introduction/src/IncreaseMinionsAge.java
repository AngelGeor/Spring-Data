import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class IncreaseMinionsAge {
    private static final String GET_ALL_MINIONS = "SELECT `name`, `age` FROM minions_db.minions ORDER BY id";
    private static final String UPDATE_ALL_MINIONS =
            "UPDATE minions_db.minions SET age = age + 1, `name` = lower(`name`) WHERE id IN (%s);";


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        List<String> input = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        Connection connection = Utils.getSQLConnection();

        update(input, connection);

        print(connection);

        connection.close();
    }

    private static void print(Connection connection) throws SQLException {
        PreparedStatement selectMinions = connection.prepareStatement(GET_ALL_MINIONS);
        ResultSet print = selectMinions.executeQuery();

        while (print.next()) {
            String name = print.getString("name");
            int age = print.getInt("age");

            System.out.println(name + " " + age);
        }
    }

    private static void update(List<String> minionsId, Connection connection) throws SQLException {
        String updateMinionsSQL = String.format(UPDATE_ALL_MINIONS,
                minionsId.stream()
                        .map(v -> "?")
                        .collect(Collectors.joining(", ")));

        PreparedStatement update = connection.prepareStatement(updateMinionsSQL);

        for(int i = 1; i <= minionsId.size(); i++) {
            update.setInt(i, Integer.parseInt(minionsId.get(i-1)));
        }

        update.executeUpdate();
    }
}