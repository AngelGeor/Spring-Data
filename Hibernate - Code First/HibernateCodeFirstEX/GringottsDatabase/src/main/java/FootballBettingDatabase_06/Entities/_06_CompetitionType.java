package FootballBettingDatabase_06.Entities;

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
@Table(name = "_06_Competition_Type")
public class _06_CompetitionType extends BaseEntity_06 {

    @Column
    private String name;
}