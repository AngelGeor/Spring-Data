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
public class _06_BetGame implements Serializable {

    @Id
    @OneToOne
    private _06_Game game06;

    @Id
    @OneToOne
    private _06_Bet bet06;

    @OneToOne
    @JoinColumn
    private _06_ResultPrediction resultPrediction06;
}