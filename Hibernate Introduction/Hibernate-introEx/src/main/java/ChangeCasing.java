import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ChangeCasing {

    public static void main(String[] args) {
       final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
       final EntityManager entityManager = entityManagerFactory.createEntityManager();


       entityManager.getTransaction().begin();

       final Query towns = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> results = towns.getResultList();
        for(Town town : results) {
            String townName = town.getName();
            if(townName.length() <= 5) {
                town.setName(townName.toUpperCase());
                entityManager.persist(town);
            }
        }


       entityManager.getTransaction().commit();
       entityManager.close();
    }
}
