package antonionorfo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table
public class Riviste extends Catalogo {
    private Peridiocita peridiocita;

    public Riviste() {
    }

    public Riviste(String titolo, LocalDate annoDiPubblicazione, int numeroPagine, Peridiocita peridiocita) {
        super(titolo, annoDiPubblicazione, numeroPagine);
        this.peridiocita = peridiocita;
    }

    public Peridiocita getPeridiocita() {
        return peridiocita;
    }

    public void setPeridiocita(Peridiocita peridiocita) {
        this.peridiocita = peridiocita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                "peridiocita=" + peridiocita +
                '}';
    }
}

