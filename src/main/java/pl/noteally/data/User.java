package pl.noteally.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private List<Catalog> catalogs;

    @Basic
    @NotBlank(message = "Must not be empty")
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    @Column(name = "login", nullable = false)
    @Pattern(regexp = "^[a-z]+$", message = "The field must contains only small letters")
    private String login;

    @Basic
    @NotBlank(message = "Must not be empty")
    @Size(min = 6, message = "Must be longer than 5 letters")
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @NotBlank(message = "Must not be empty")
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "The field must start with a capital letter and contains only letters")
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    private String name;

    @Basic
    @NotBlank(message = "Must not be empty")
    @Column(name = "surname", nullable = false)
    @Pattern(regexp = "[A-Z][a-zA-Z]*", message = "The field must start with a capital letter and contains only letters")
    @Size(min = 3, max = 50, message = "Size must be between 3 and 50")
    private String surname;

    @Basic
    @Email(message = "Must be a properly formatted e-mail address")
    @NotBlank(message = "Must not be empty")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Basic
    @Column(name = "age", nullable = false)
    @DecimalMin(value = "18", message = "Must be 18 or older")
    private Integer age;

    private enum role{GUEST, LOGGED, ADMIN};
}

