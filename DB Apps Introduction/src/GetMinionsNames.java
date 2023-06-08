import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionsNames {
    private static final String GET_MINIONS_NAME_AGE = "SELECT m.name,m.age" +
            " from minions_db.minions as m" +
            " JOIN minions_db.minions_villains as mv ON m.id = mv.minion_id" +
            " where mv.villain_id = ?";

    private static final String NO_VILLAIN = "No villain with ID %d exists in the database.";
    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT v.name FROM minions_db.villains as v where v.id = ?";
    private static final String PRINT_FORMAT_VIL = "Villain: %s%n";
    private static final String PRINT_FORMAT_MIN = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {
       final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        int villainInd = Integer.parseInt(scanner.nextLine());
        PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        villainStatement.setInt(1,villainInd);

        final ResultSet villainSet = villainStatement.executeQuery();
        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN,villainInd);
            connection.close();
            return;
        }
        final String villainName = villainSet.getString("name");
        System.out.printf(PRINT_FORMAT_VIL, villainName);
        final PreparedStatement minionsResult =  connection.prepareStatement(GET_MINIONS_NAME_AGE);
        minionsResult.setInt(1,villainInd);

        final ResultSet minionsSet = minionsResult.executeQuery();

        for (int i = 1; minionsSet.next(); i++) {
            final String minionName = minionsSet.getString("name");
            final int minionAge = minionsSet.getInt("age");
            System.out.printf(PRINT_FORMAT_MIN,i,minionName,minionAge);
        }
        connection.close();
    }
}
