package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_ResultPrediction extends BaseEntity_06 {

    @Enumerated(EnumType.STRING)
    private _06_ResultPredictionValues resultPrediction;
}