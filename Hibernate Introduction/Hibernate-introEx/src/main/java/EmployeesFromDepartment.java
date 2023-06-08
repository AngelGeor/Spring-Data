import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesFromDepartment {
    private static final String PRINT_FORMAT = "%s %s from Research and Development - $%.2f%n";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();



        entityManager.createQuery(
          "SELECT e FROM Employee e WHERE e.department.name = 'Research and Development' ORDER BY e.salary ASC, e.id",
                        Employee.class)
                .getResultList()
                .forEach(e-> System.out.printf(PRINT_FORMAT,e.getFirstName(),e.getLastName()
                       ,e.getSalary()));


        entityManager.close();
    }
}
