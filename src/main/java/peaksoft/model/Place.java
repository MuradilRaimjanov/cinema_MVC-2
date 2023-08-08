package peaksoft.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String x;

    private  int y;

    private int price;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    },fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;
}