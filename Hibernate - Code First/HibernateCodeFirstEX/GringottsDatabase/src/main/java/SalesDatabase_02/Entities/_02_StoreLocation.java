package SalesDatabase_02.Entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "_02_store_location")
public class _02_StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @OneToMany(targetEntity = _02_Sale.class, mappedBy = "a02StoreLocation")
    private Set<_02_Sale> a01Sales;

    public _02_StoreLocation(){}

    public _02_StoreLocation(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
