package sincity.bot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sincity.bot.domain.enums.Stations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "route_tbl")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="route_user_tbl",
            joinColumns = {@JoinColumn(name = "route_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private List<User> users= new ArrayList<>();
    @NotNull
    private Stations departure;
    @NotNull
    private Stations arrivel;
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "route_date")
    private Date date;
    private Integer placeCount;
    private String comment;
}
