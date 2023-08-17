package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Cinema;
import peaksoft.model.Movie;
import peaksoft.model.Room;
import peaksoft.service.ServiceLayer;

import java.util.List;

@Service
@Transactional
public class RoomService implements ServiceLayer<Room> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Room save(Room room) {
        Cinema cinema = entityManager.find(Cinema.class, room.getCinemaId());
        room.setCinema(cinema);
        entityManager.persist(room);
        return room;
    }

    @Override
    public Room findById(int id) {
        return entityManager.find(Room.class, id);
    }

    @Override
    public List<Room> findAll() {
        return (List<Room>) entityManager.createQuery("from Room ").getResultList();
    }

    public List<Room> findAllId(int id) {
        return (List<Room>) entityManager.createQuery("from Room r where r.cinema.id =:id", Room.class).setParameter("id", id).getResultList();
    }

    @Override
    public Room update(int id, Room room) {
        Room oldRoom = entityManager.find(Room.class, id);
        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        entityManager.merge(oldRoom);
        return oldRoom;
    }

    @Override
    public void deleteById(int id) {
        Room room = entityManager.find(Room.class,id);
        room.setCinema(null);
        entityManager.remove(room);
    }

    @Override
    public Room findByName(String name) {
        return entityManager.createQuery("from Room r where r.name = :nameOfRoom", Room.class).setParameter("nameOfRoom", name).getSingleResult();
    }
}

