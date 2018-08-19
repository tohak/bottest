package sincity.bot.domain;

import lombok.*;
import sincity.bot.domain.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_tbl")
public class User {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @NotNull
    @Column(name = "user_name")
    private String userName;
    @NotNull
    @Column(name = "chat_id")
    private Long chatId;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user2roles_tbl",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            foreignKey = @ForeignKey(name = "users2roles_user_fk"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "roles_enum"}))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles_enum")
    private Set<Role> roles = new HashSet<>();
    @NotNull
    @Column(name = "user_state")
    private Integer state;
    @NotNull
    @Column(name = "tg_link")
    private String telegramLink;
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Route> routes= new ArrayList<>();

    public User(Long userId, @NotNull String userName, @NotNull Long chatId, Set<Role> roles, @NotNull Integer state, @NotNull String telegramLink) {
        this.userId = userId;
        this.userName = userName;
        this.chatId = chatId;
        this.roles = roles;
        this.state = state;
        this.telegramLink = telegramLink;
    }
}
