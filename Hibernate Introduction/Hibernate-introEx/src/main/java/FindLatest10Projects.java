import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {
    public static void main(String[] args) {
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
         EntityManager entityManager = entityManagerFactory.createEntityManager();

         entityManager.createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC", Project.class)
                 .setMaxResults(10)
                 .getResultList()
                 .stream()
                 .sorted(Comparator.comparing(Project::getName))
                 .forEach(System.out::println);

         entityManager.close();
    }
}
