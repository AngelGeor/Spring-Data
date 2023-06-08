package BillsPaymentSystem_05.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "_05_bank_account")
public class _05_BankAccount extends _05_BillingDetail{

    @Column(name = "bank")
    private String name;

    @Column(name = "swift")
    private String swiftCode;
}
