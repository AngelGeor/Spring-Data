package FootballBettingDatabase_06.Entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table
public class _06_Bet extends BaseEntity_06 {

    @Column
    private BigDecimal betMoney;

    @Column
    private Date timeOfBet;

    @ManyToOne
    private _06_User user06;
}