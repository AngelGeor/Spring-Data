package SalesDatabase_02;

import SalesDatabase_02.Entities._02_Customer;
import SalesDatabase_02.Entities._02_Product;
import SalesDatabase_02.Entities._02_Sale;
import SalesDatabase_02.Entities._02_StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class SalesMain {

    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        _02_Product a01Product = new _02_Product("TV", 234, BigDecimal.TEN);
        _02_Customer a01Customer = new _02_Customer("Misho", "misho@gmail.com", "334532563");
        _02_StoreLocation location = new _02_StoreLocation("Sofia");

        _02_Sale a01Sale = new _02_Sale(a01Product, a01Customer,location);

        entityManager.persist(a01Product);
        entityManager.persist(a01Customer);
        entityManager.persist(location);
        entityManager.persist(a01Sale);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

