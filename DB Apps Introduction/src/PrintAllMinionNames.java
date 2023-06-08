import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintAllMinionNames {

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        PreparedStatement allMinionsNames = connection.prepareStatement(
                "SELECT m.name from minions_db.minions as m order by m.id");
        ResultSet resSet = allMinionsNames.executeQuery();

        ArrayDeque<String> names = new ArrayDeque<>();

        while (resSet.next()) {
            names.offer(resSet.getString("name"));
        }

        while (names.size() > 2){
            System.out.println(names.pollFirst());
            System.out.println(names.pollLast());
        }

        while(!names.isEmpty()) {
            System.out.println(names.poll());
        }
        connection.close();
    }
}
