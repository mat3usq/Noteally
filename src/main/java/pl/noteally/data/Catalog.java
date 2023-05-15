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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "catalogId")
    private List<Note> notes;

    @Basic
    @NotBlank(message = "Must not be empty")
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^[a-z]+$", message = "The field must contains only small letters")
    private String name;
}
