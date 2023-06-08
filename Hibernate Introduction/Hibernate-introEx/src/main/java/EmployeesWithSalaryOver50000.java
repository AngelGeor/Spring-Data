import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("Select e.firstName FROM Employee e WHERE e.salary > 50000", String.class)
        .getResultList()
                .forEach(name -> System.out.println(name));

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
