package antonionorfo;

import antonionorfo.Dao.CatalogoDao;
import antonionorfo.Entities.Genere;
import antonionorfo.Entities.Libri;
import antonionorfo.Entities.Peridiocita;
import antonionorfo.Entities.Riviste;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgettoJava3");

        EntityManager em = emf.createEntityManager();


        CatalogoDao catalogoDao = new CatalogoDao(em);

        Libri libro1 = new Libri("Il Signore degli Anelli", LocalDate.of(1954, 7, 29), 1200, "J.R.R. Tolkien", Genere.FANTASCIENZA);

        catalogoDao.save(libro1);

        Riviste rivista1 = new Riviste("National Geographic", LocalDate.of(2024, 1, 1), 100, Peridiocita.MENSILE);

        catalogoDao.save(rivista1);


        em.close();
        emf.close();
    }
}
