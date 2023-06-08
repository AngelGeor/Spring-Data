package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_PlayerStatistics implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private _06_Game game06;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private _06_Player player06;

    @Column(name = "scoredGoals")
    private short scoredGoals;

    @Column(name = "assists")
    private short assists;

    @Column(name = "minutes_played")
    private short minutesPlayed;

}
