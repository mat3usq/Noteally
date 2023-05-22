package pl.noteally.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name="catalogs")
public class Catalog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "catalog")
    private List<Note> notes;

    @Basic
    @Column(name = "name", unique = true, nullable = false)
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    @NotBlank(message = "Must not be empty")
    @Pattern(regexp = "^[a-z]+$", message = "Name must contain only small letters.")
    private String name;
}
