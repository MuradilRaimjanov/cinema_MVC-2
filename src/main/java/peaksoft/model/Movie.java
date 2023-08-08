package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.enums.Genre;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private LocalDate created;
    private String country;
    private String language;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "movie")
    private List<Session> sessions;
}
