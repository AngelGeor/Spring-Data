package HospitalDatabase_04.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class _04_Diagnose extends _04_BaseEntity{


    @Column(nullable = false)
    private String name;

    @Column
    private String comments;

    @ManyToMany(mappedBy = "diagnoses")
    private Set<_04_Patient> patients;

}
