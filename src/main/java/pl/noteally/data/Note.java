package pl.noteally.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name="notes")
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "title", nullable = false)
    @Size(min = 3, max = 20, message = "Size must be between 3 and 20")
    @NotBlank(message = "Must not be empty")
    private String title;

    @Basic
    @Column(name = "content", nullable = false)
    @Size(min = 5, max = 500, message = "Size must be between 5 and 500")
    @NotBlank(message = "Must not be empty")
    private String content;

    @Basic
    @Column(name = "link")
    @Size(min = 5, max = 50, message = "Size must be between 5 and 500")
    private String link;

    @Basic
    @CreationTimestamp
    @Column(name = "date", nullable = false, columnDefinition = "date default current_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
}
