import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
     final Connection conn = Utils.getSQLConnection();

     final PreparedStatement st = conn.prepareStatement("SELECT v.name, count(distinct mv.minion_id) as minions_count FROM minions_db.villains as v\n" +
             " JOIN minions_db.minions_villains as mv ON v.id = mv.villain_id" +
             " group by mv.villain_id" +
             " having minions_count > ? " +
             " order by minions_count" );
     st.setInt(1, 15);
        final ResultSet result = st.executeQuery();

        while (result.next()) {

            final String villainName = result.getString("name");
            final  int numberOfMinions = result.getInt("minions_count");
            System.out.printf("%s %d", villainName,numberOfMinions);
        }
        conn.close();
    }

}
