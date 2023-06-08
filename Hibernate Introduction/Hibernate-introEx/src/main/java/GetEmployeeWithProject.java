
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        int id = Integer.parseInt(scanner.nextLine());

       String result = entityManager.createQuery("SELECT e from Employee e WHERE e.id = :id", Employee.class)
        .setParameter("id", id)
                .getResultList()
                .toString();

        System.out.print(result.toString().replaceAll("[\\[\\],]", ""));
    }
}
