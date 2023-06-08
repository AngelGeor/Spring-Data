package BillsPaymentSystem_05.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class _05_BillingDetail {
    @Id
    @Column(unique = true)
    private String number;

    @ManyToOne
    private _05_User owner;
}
