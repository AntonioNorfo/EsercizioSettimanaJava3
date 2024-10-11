package antonionorfo.Dao;

import antonionorfo.Entities.Catalogo;
import jakarta.persistence.EntityManager;

import java.util.UUID;

public class CatalogoDao {

    private EntityManager em;

    public CatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo catalogo) {
        em.getTransaction().begin();
        em.persist(catalogo);
        em.getTransaction().commit();
    }

    public Catalogo findById(UUID id) {
        return em.find(Catalogo.class, id);
    }

    public void update(Catalogo catalogo) {
        em.getTransaction().begin();
        em.merge(catalogo);
        em.getTransaction().commit();
    }

    public void delete(Catalogo catalogo) {
        em.getTransaction().begin();
        em.remove(em.contains(catalogo) ? catalogo : em.merge(catalogo));
        em.getTransaction().commit();
    }
}
