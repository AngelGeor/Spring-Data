package HospitalDatabase_04.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class _04_Medicament extends _04_BaseEntity{
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "prescriptions")
    private Set<_04_Patient> patients;

}
