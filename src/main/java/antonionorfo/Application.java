package antonionorfo;

import antonionorfo.Dao.CatalogoDao;
import antonionorfo.Entities.*;
import antonionorfo.Exception.ElementoNonTrovatoException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        Libri libro4 = new Libri("Spiderman", LocalDate.of(1644, 9, 28), 780, "Tolkien", Genere.STORICO);
        Libri libro5 = new Libri("DB budokai", LocalDate.of(1396, 1, 15), 900, "Spiderman", Genere.BIOGRAFICO);
        Libri libro6 = new Libri("Bleach", LocalDate.of(1934, 5, 26), 100, "La casa de papel", Genere.GIALLO);

        catalogoDao.save(libro1);
        catalogoDao.save(libro2);
        catalogoDao.save(libro3);
        catalogoDao.save(libro4);
        catalogoDao.save(libro5);
        catalogoDao.save(libro6);

        Riviste rivista1 = new Riviste("Paperino e Pluto", LocalDate.of(1986, 7, 25), 130, Peridiocita.MENSILE);
        Riviste rivista2 = new Riviste("Game of Thrones", LocalDate.of(2012, 9, 16), 590, Peridiocita.SETTIMANALE);
        Riviste rivista3 = new Riviste("FOCUS", LocalDate.of(2000, 6, 4), 6500, Peridiocita.SEMESTRALE);
        Riviste rivista4 = new Riviste("FBI", LocalDate.of(1384, 10, 13), 1790, Peridiocita.MENSILE);
        Riviste rivista5 = new Riviste("JhonConnor", LocalDate.of(2019, 12, 12), 2590, Peridiocita.SEMESTRALE);
        Riviste rivista6 = new Riviste("Bankai", LocalDate.of(1234, 3, 16), 550, Peridiocita.SETTIMANALE);

        catalogoDao.save(rivista1);
        catalogoDao.save(rivista2);
        catalogoDao.save(rivista3);
        catalogoDao.save(rivista4);
        catalogoDao.save(rivista5);
        catalogoDao.save(rivista6);

        try {
            Catalogo catalogo = catalogoDao.findById(UUID.fromString("1f2ce0eb-0c3a-4cf7-abb0-0e7c1c2fe234"));
            System.out.println("\n Elemento trovato: " + catalogo);
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }


        try {
            catalogoDao.deleteById(UUID.fromString("0cabc35e-fab4-4acf-82f3-45382cd42b47"));
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
            System.out.println("\n Inserisci un nuovo elemento per continuare:");
        }


        try {
            List<Catalogo> cataloghi = catalogoDao.findByAnnoDiPubblicazione(LocalDate.of(1234, 3, 16));

            String risultato = cataloghi.stream()
                    .map(Catalogo -> Catalogo.toString())
                    .reduce("", (acc, catalogo) -> acc + catalogo + "\n");

            System.out.println("\n Elementi che corrispondono all anno inserito:\n" + risultato);
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }


        try {
            List<Libri> libriDiTolkien = catalogoDao.findByAutore("Tolkien");

            String risultato = libriDiTolkien.stream()
                    .map(Libri -> Libri.toString())
                    .reduce("", (acc, libro) -> acc + libro + "\n");
            System.out.println("\nTutti i libri che hanno questo autore sono:\n" + risultato);
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }


        try {
            List<Catalogo> cataloghi = catalogoDao.findByTitolo("Spiderman");

            String risultato = cataloghi.stream()
                    .map(Catalogo -> Catalogo.toString())
                    .reduce("", (acc, catalogo) -> acc + catalogo + "\n");

            System.out.println("\n Elementi che corrispondono al titolo inserito:\n" + risultato);
        } catch (ElementoNonTrovatoException e) {
            System.out.println(e.getMessage());
        }


        em.close();
        emf.close();
    }
}
