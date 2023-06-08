package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_Town extends BaseEntity_06 {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private _06_Country country06;
}