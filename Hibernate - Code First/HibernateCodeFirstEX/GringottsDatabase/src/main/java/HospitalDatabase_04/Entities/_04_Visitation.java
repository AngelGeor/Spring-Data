package HospitalDatabase_04.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table

public class _04_Visitation extends _04_BaseEntity{

    @Column(nullable = false)
    private Date date;

    @Column()
    private String comments;

    @ManyToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private _04_Patient patient;

}
