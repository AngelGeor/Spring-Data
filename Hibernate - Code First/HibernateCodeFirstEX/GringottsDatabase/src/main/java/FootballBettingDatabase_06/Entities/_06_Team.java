package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "_06_teams")
public class _06_Team extends BaseEntity_06 {

    @Column(nullable = false)
    private String name;

    @Column
    private String logo;

    @Column(length = 4, nullable = false)
    private String initials;

    @ManyToOne
    @JoinColumn
    private _06_Color primaryColor06;

    @ManyToOne
    @JoinColumn
    private _06_Color secondaryColor06;

    @ManyToOne
    private _06_Town town06;

    @Column
    private BigInteger budget;

}