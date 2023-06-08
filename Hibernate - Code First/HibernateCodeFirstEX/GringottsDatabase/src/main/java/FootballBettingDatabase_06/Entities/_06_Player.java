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
public class _06_Player extends BaseEntity_06 {

    @Column(nullable = false)
    private String name;

    @Column(name = "squad_number", nullable = false)
    private short squadNumber;

    @ManyToOne
    private _06_Team team06;

    @ManyToOne
    private _06_Position position06;

    @Column(name = "is_currently_injured")
    private Boolean isCurrentlyInjured;

}