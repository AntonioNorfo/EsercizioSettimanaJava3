package antonionorfo.Dao;

import antonionorfo.Entities.Utente;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class UtenteDao {

    private EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public Utente findById(UUID id) {
        return em.find(Utente.class, id);
    }

    public void delete(Utente utente) {
        em.getTransaction().begin();
        em.remove(em.contains(utente) ? utente : em.merge(utente));
        em.getTransaction().commit();
    }
}
