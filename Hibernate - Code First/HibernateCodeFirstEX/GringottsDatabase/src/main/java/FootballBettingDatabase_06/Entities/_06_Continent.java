package FootballBettingDatabase_06.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table()
public class _06_Continent extends BaseEntity_06 {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "continent06s")
    private Set<_06_Country> countries;
}