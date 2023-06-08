import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinion {
    private static final String GET_TOWN_BY_NAME = "SELECT t.id from minions_db.towns as t where t.name = ?";
    private static final String INSERT_INTO_TOWNS = "INSERT INTO minions_db.towns(name) value (?)";
    private static final String ADDED_TOWN_FORMAT = "Town %s was added to the database.%n";
    private static final String GET_VILLAIN_BY_NAME = "SELECT v.id from minions_db.villains as v where v.name = ?";
    private static final String INSERT_VILLAIN = "INSERT INTO minions_db.villains(name,evilness_factor) value (?,?)";
    private static final String VILLAIN_ADDED_FORMAT = "Villain %s was added to the database.%n";
    private static final String EVILNESS_FACTOR = "evil";
    private static final String INSERT_INTO_MINIONS = "INSERT INTO minions_db.minions(name,age,town_id) values (?,?,?)";
    private static final String SELECT_LAST_MINION = "SELECT m.id from minions_db.minions as m order by m.id desc limit 1";
    private static final String INSERT_INTO_MINIONS_VILLAINS =
            "INSERT into minions_db.minions_villains(minion_id,villain_id) values(?,?)";
    private static final String RESULT_FORMAT = "Successfully added %s to be minion of %s%n";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();
        Scanner scanner = new Scanner(System.in);

       String[] minionInfo =  scanner.nextLine().split("\\s+");
        String minName = minionInfo[1];
        int minAge = Integer.parseInt(minionInfo[2]);
        String minTown = minionInfo[3];

        String vilName = scanner.nextLine().split("\\s+")[1];

        int townId = getId(connection, List.of(minTown),GET_TOWN_BY_NAME, INSERT_INTO_TOWNS,ADDED_TOWN_FORMAT);
        int villainId = getId(connection,List.of(vilName,EVILNESS_FACTOR),GET_VILLAIN_BY_NAME, INSERT_VILLAIN,
                VILLAIN_ADDED_FORMAT);

        PreparedStatement minionStatement = connection.prepareStatement(INSERT_INTO_MINIONS);
        minionStatement.setString(1,minName);
        minionStatement.setInt(2,minAge);
        minionStatement.setInt(3,townId);

        minionStatement.executeUpdate();

       PreparedStatement lastAddedMinion = connection.prepareStatement(SELECT_LAST_MINION);
       ResultSet resSet = lastAddedMinion.executeQuery();
        resSet.next();


       int lastAddedMinionId = resSet.getInt("id");

       PreparedStatement insertIntoMinVil = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        insertIntoMinVil.setInt(1,lastAddedMinionId);
        insertIntoMinVil.setInt(2,villainId);

        insertIntoMinVil.executeUpdate();

        System.out.printf(RESULT_FORMAT, minName,vilName);

        connection.close();

    }
    private static int getId(Connection connection,
                             List<String> arguments,
                             String selectQuery,
                             String insertQuery,
                             String printFormat) throws SQLException {
        String name = arguments.get(0);

        PreparedStatement townStatement = connection.prepareStatement(selectQuery);
        townStatement.setString(1,name);

        final ResultSet townSet = townStatement.executeQuery();

        if(!townSet.next()) {
            final PreparedStatement statement = connection.prepareStatement(insertQuery);
            for (int i = 1; i <= arguments.size(); i++) {
                statement.setString(i,arguments.get(i - 1));

            }

            statement.executeUpdate();

            ResultSet newResultSet = townStatement.executeQuery();
            newResultSet.next();
           int id = newResultSet.getInt("id");

            System.out.printf(printFormat,name);
            return id;
        }

       return townSet.getInt("id");
    }
}
