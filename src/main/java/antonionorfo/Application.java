package antonionorfo;

import antonionorfo.Dao.CatalogoDao;
import antonionorfo.Entities.Genere;
import antonionorfo.Entities.Libri;
import antonionorfo.Entities.Peridiocita;
import antonionorfo.Entities.Riviste;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Random;

public class Application {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProgettoJava3");

        EntityManager em = emf.createEntityManager();

        Faker faker = new Faker();
        Random random = new Random();


        CatalogoDao catalogoDao = new CatalogoDao(em);

        Libri libro1 = new Libri("Il Signore degli Anelli", LocalDate.of(1954, 7, 29), 1200, "Tolkien", Genere.FANTASCIENZA);
        Libri libro2 = new Libri("Marvel", LocalDate.of(1996, 8, 6), 500, "Spiderman", Genere.THRILLER);
        Libri libro3 = new Libri("Harry Potter", LocalDate.of(1997, 6, 26), 1900, "La casa de papel", Genere.HORROR);

        catalogoDao.save(libro1);
        catalogoDao.save(libro2);
        catalogoDao.save(libro3);

        Riviste rivista1 = new Riviste("Paperino e Pluto", LocalDate.of(1986, 7, 25), 130, Peridiocita.MENSILE);
        Riviste rivista2 = new Riviste("Game of Thrones", LocalDate.of(2012, 9, 16), 590, Peridiocita.SETTIMANALE);
        Riviste rivista3 = new Riviste("FOCUS", LocalDate.of(2000, 6, 4), 300, Peridiocita.SEMESTRALE);

        catalogoDao.save(rivista1);
        catalogoDao.save(rivista2);
        catalogoDao.save(rivista3);


        em.close();
        emf.close();
    }
}
