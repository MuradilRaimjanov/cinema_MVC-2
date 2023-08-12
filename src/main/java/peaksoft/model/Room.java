package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Double rating;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_id")
    @ToString.Exclude
    private Cinema cinema;


    @Transient
    private int cinemaId;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room",fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Place> places;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rooms")
    private List<Session> session;

}