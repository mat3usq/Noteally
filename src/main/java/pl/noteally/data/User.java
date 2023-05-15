package pl.noteally.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name="users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Catalog> catalogs;

    private String login;

    private String password;

    private String name;

    private String surname;

    private String email;

    private Integer age;

    private enum role{GUEST, LOGGED, ADMIN};
}

