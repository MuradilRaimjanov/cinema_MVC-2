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
    })
    @JoinColumn(name = "cinema_id")
    @ToString.Exclude
    private Cinema cinema;
    @Transient
    private int cinemaId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
    @ToString.Exclude
    private List<Place> places;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "session_and_room",
            joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<Session> session;

}