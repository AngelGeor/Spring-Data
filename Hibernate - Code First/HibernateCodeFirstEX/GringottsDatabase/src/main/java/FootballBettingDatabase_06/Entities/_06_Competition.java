package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_Competition extends BaseEntity_06 {


    @Column
    private String name;

    @ManyToOne
    private _06_CompetitionType type;
}