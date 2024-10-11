package antonionorfo.Dao;

import antonionorfo.Entities.Catalogo;
import antonionorfo.Entities.Libri;
import antonionorfo.Exception.ElementoNonTrovatoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
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
        Catalogo catalogo = em.find(Catalogo.class, id);
        if (catalogo == null) {
            throw new ElementoNonTrovatoException("Elemento non trovato nel catalogo con id: " + id);
        }
        return catalogo;
    }


    public List<Catalogo> findByAnnoDiPubblicazione(LocalDate annoDiPubblicazione) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT c FROM Catalogo c WHERE c.annoDiPubblicazione = :anno", Catalogo.class);
        query.setParameter("anno", annoDiPubblicazione);

        List<Catalogo> results = query.getResultList();
        if (results.isEmpty()) {
            throw new ElementoNonTrovatoException("Non abbiamo trovato alcun elemento associato all'anno di pubblicazione: " + annoDiPubblicazione);
        }
        return results;
    }


    public List<Libri> findByAutore(String autore) {
        System.out.println("Esecuzione della query per l'autore: " + autore);
        TypedQuery<Libri> query = em.createQuery(
                "SELECT l FROM Libri l WHERE l.autore = :autore", Libri.class);
        query.setParameter("autore", autore);

        List<Libri> results = query.getResultList();
        System.out.println("Risultati trovati: " + results.size());
        if (results.isEmpty()) {
            throw new ElementoNonTrovatoException("Non abbiamo trovato alcun libro dell'autore: " + autore);
        }
        return results;
    }


    public List<Catalogo> findByTitolo(String parteDelTitolo) {
        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT c FROM Catalogo c WHERE c.titolo LIKE :titolo", Catalogo.class);
        query.setParameter("titolo", "%" + parteDelTitolo + "%");

        List<Catalogo> results = query.getResultList();

        if (results.isEmpty()) {
            throw new ElementoNonTrovatoException("Non abbiamo trovato alcun elemento associato al titolo o a qualche carattere associato: " + parteDelTitolo);
        }

        return results;
    }


    public void deleteById(UUID id) {
        em.getTransaction().begin();
        Catalogo catalogo = em.find(Catalogo.class, id);
        if (catalogo == null) {
            throw new ElementoNonTrovatoException("Non abbiamo trovato questo elemento specifico : " + id);
        }
        em.remove(catalogo);
        em.getTransaction().commit();
    }
}