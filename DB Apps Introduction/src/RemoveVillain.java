import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {
    private static final String GET_VILLAIN_BY_ID ="SELECT v.name FROM minions_db.villains as v where id = ?";
    private static final String MINION_COUNT =
            "SELECT COUNT(mv.minion_id) as min_count FROM minions_db.minions_villains as mv where mv.villain_id = ?";
    private static final String NON_EXISTENT_VILLAIN ="No such villain was found";
    private static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n";
    private static final String DELETE_MINIONS_VILLAINS_BY_VIL_ID =
            "DELETE FROM minions_db.minions_villains as mv WHERE mv.villain_id = ?";

    private static final String DELETE_VILLAIN_BY_ID ="DELETE FROM minions_db.villains as v where v.id = ?";
    private static final String DELETED_COUNT_OF_MINIONS_FORMAT ="%d minions released%n";



    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();
        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        final PreparedStatement selectedVillain = connection.prepareStatement(GET_VILLAIN_BY_ID);
        selectedVillain.setInt(1,villainId);
        ResultSet resSet = selectedVillain.executeQuery();

        if(!resSet.next()){
            System.out.println(NON_EXISTENT_VILLAIN);
            return;
        }
       String villainName = resSet.getString("name");

        PreparedStatement selectAllMinions = connection.prepareStatement
         (MINION_COUNT);
        selectAllMinions.setInt(1,villainId);

        selectAllMinions.executeQuery();
        ResultSet countOfMinions = selectAllMinions.executeQuery();
        countOfMinions.next();

        int countOfDeletedMinions = countOfMinions.getInt("min_count");

        connection.setAutoCommit(false);

        try (
              PreparedStatement deleteMinionStatement =  connection.prepareStatement(DELETE_MINIONS_VILLAINS_BY_VIL_ID);
              PreparedStatement deleteVillainStatement =  connection.prepareStatement(DELETE_VILLAIN_BY_ID)) {

              deleteMinionStatement.setInt(1,villainId);
              deleteMinionStatement.executeUpdate();

              deleteVillainStatement.setInt(1,villainId);
              deleteVillainStatement.executeUpdate();

              connection.commit();
            System.out.printf(DELETED_VILLAIN_FORMAT,villainName);
            System.out.printf(DELETED_COUNT_OF_MINIONS_FORMAT,countOfDeletedMinions);

        }catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        connection.close();


    }
}
