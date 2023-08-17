package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import peaksoft.model.Cinema;
import peaksoft.model.Place;
import peaksoft.model.Room;
import peaksoft.service.ServiceLayer;

import java.util.List;

@Service
@Transactional
public class PlaceService implements ServiceLayer<Place> {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Place save(Place place) {
        Room room = entityManager.find(Room.class, place.getRoomId());
        place.setRoom(room);
        entityManager.persist(place);
        return place;
    }
    @Override
    public Place findById(int id) {
        return entityManager.find(Place.class, id);
    }
    @Override
    public List<Place> findAll() {
        return (List<Place>) entityManager.createQuery("from Place").getResultList();
    }

    @Override
    public Place update(int id, Place place) {
        Place oldPlace = entityManager.find(Place.class, id);
        oldPlace.setPrice(place.getPrice());
        oldPlace.setX(place.getX());
        oldPlace.setY(place.getY());
        entityManager.merge(oldPlace);
        return oldPlace;
    }
    @Override
    public void deleteById(int id) {
        entityManager.remove(entityManager.find(Place.class, id));
    }

    public List<Place> findAllId(int id) {
        return (List<Place>) entityManager.createQuery("from Place p where p.room.id =:id", Place.class).setParameter("id", id).getResultList();
    }

    @Override
    public Place findByName(String name) {
        return null;
    }
}
