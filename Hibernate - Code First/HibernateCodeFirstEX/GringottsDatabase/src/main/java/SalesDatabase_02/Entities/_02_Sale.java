package SalesDatabase_02.Entities;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "_02_sales")
public class _02_Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private _02_Product a01Product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private _02_Customer a01Customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private _02_StoreLocation a02StoreLocation;

    public _02_Sale() {}

    public _02_Sale(_02_Product a01Product, _02_Customer a01Customer, _02_StoreLocation a02StoreLocation) {
        this.a01Product = a01Product;
        this.a01Customer = a01Customer;
        this.a02StoreLocation = a02StoreLocation;

        this.date = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public _02_Product getProduct() {
        return a01Product;
    }

    public void setProduct(_02_Product a01Product) {
        this.a01Product = a01Product;
    }

    public _02_Customer getCustomer() {
        return a01Customer;
    }

    public void setCustomer(_02_Customer a01Customer) {
        this.a01Customer = a01Customer;
    }

    public _02_StoreLocation getStoreLocation() {
        return a02StoreLocation;
    }

    public void setStoreLocation(_02_StoreLocation a02StoreLocation) {
        this.a02StoreLocation = a02StoreLocation;
    }
}
