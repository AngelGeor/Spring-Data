
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        String[] input = scanner.nextLine().split("\\s+");

        String firstName = input[0];
        String lastName = input[1];

        Long countOfMatches = em.createQuery(
                "SELECT count(e) FROM Employee e WHERE e.firstName = :fn AND e.lastName = :ln", Long.class)
                .setParameter("fn", firstName)
                .setParameter("ln", lastName)
                .getSingleResult();

        if(countOfMatches == 0){
            System.out.println("No");
        }else {
            System.out.println("Yes");
        }

    }
}
