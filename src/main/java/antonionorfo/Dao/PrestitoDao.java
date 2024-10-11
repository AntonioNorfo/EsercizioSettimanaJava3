package antonionorfo.Dao;

import antonionorfo.Entities.Prestito;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class PrestitoDao {

    private EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }

    public Prestito findById(UUID id) {
        return em.find(Prestito.class, id);
    }

    public void delete(Prestito prestito) {
        em.getTransaction().begin();
        em.remove(em.contains(prestito) ? prestito : em.merge(prestito));
        em.getTransaction().commit();
    }
}
