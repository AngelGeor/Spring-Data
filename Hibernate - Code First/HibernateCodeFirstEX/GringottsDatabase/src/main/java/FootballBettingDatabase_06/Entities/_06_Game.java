package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_Game extends BaseEntity_06 implements Serializable {
    @OneToOne
    @JoinColumn
    private _06_Team homeTeam06;

    @OneToOne
    @JoinColumn
    private _06_Team awayTeam06;

    @Column
    private Short homeGoals;

    @Column
    private Short awayGoals;

    @Column
    private Date dateAndTime;

    @Column
    private double homeTeamWinBetRate;

    @Column
    private double awayTeamWinBetRate;

    @Column
    private double drawGameBetRate;

    @ManyToOne
    @JoinColumn
    private _06_Round round06;

    @ManyToOne
    @JoinColumn
    private _06_Competition competition06;

}
