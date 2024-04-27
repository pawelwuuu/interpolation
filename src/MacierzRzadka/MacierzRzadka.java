package MacierzRzadka;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Klasa reprezentująca rzadką macierz.
 */
public class MacierzRzadka {

    /** Rozmiar macierzy. */
    public int rozmiarMacierzy;

    /** Mapa przechowująca rzadką macierz. */
    private final LinkedHashMap<Para, Double> macierzRzadka = new LinkedHashMap<>();

    /**
     * Konstruktor tworzący nową macierz o podanym rozmiarze.
     * @param rozmiarMacierzy Rozmiar macierzy.
     */
    public MacierzRzadka(int rozmiarMacierzy) {
        this.rozmiarMacierzy = rozmiarMacierzy;
    }

    /**
     * Konstruktor tworzący macierz rzadką na podstawie zwykłej macierzy podanej jako tablica dwuwymiarowa Double.
     * @param macierzZwykla Zwykła macierz jako tablica dwuwymiarowa Double.
     */
    public MacierzRzadka(Double[][] macierzZwykla) {
        rozmiarMacierzy = macierzZwykla.length;
        for (int i = 0; i < rozmiarMacierzy; i++) {
            for (int j = 0; j < rozmiarMacierzy; j++) {
                if (macierzZwykla[i][j] != 0) {
                    ustawWartosc(i,j, macierzZwykla[i][j]);
                }
            }
        }
    }

    /**
     * Konstruktor tworzący macierz rzadką na podstawie zwykłej macierzy podanej jako tablica dwuwymiarowa double.
     * @param macierzZwykla Zwykła macierz jako tablica dwuwymiarowa double.
     */
    public MacierzRzadka(double[][] macierzZwykla) {
        rozmiarMacierzy = macierzZwykla.length;
        for (int i = 0; i < rozmiarMacierzy; i++) {
            for (int j = 0; j < rozmiarMacierzy; j++) {
                if (macierzZwykla[i][j] != 0) {
                    ustawWartosc(i,j, macierzZwykla[i][j]);
                }
            }
        }
    }

    /**
     * Klasa wewnętrzna reprezentująca parę (wiersz, kolumna).
     */
    private class Para {
        int wiersz;
        int kolumna;

        /**
         * Konstruktor klasy Para.
         * @param wiersz Numer wiersza.
         * @param kolumna Numer kolumny.
         */
        public Para(int wiersz, int kolumna) {
            this.wiersz = wiersz;
            this.kolumna = kolumna;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Para para = (Para) o;
            return wiersz == para.wiersz &&
                    kolumna == para.kolumna;
        }

        @Override
        public int hashCode() {
            return Objects.hash(wiersz, kolumna);
        }
    }

    /**
     * Sprawdza, czy wartość dla danej pary (wiersz, kolumna) jest ustawiona w macierzy.
     * @param wiersz Numer wiersza.
     * @param kolumna Numer kolumny.
     * @return true, jeśli wartość jest ustawiona, w przeciwnym razie false.
     */
    public boolean czyUstawione(int wiersz, int kolumna) {
        Para para = new Para(wiersz, kolumna);
        return macierzRzadka.containsKey(para);
    }

    /**
     * Ustawia wartość dla danej pary (wiersz, kolumna) w macierzy.
     * @param wiersz Numer wiersza.
     * @param kolumna Numer kolumny.
     * @param wartosc Wartość do ustawienia.
     */
    public void ustawWartosc(int wiersz, int kolumna, double wartosc) {
        if (wiersz >= rozmiarMacierzy || kolumna >= rozmiarMacierzy) {
            String errStr = String.format("Element w wierszu: %d kolumnie: %d jest poza zakresem macierzy", wiersz, kolumna);
            throw new WyjatekMacierz(errStr);
        }
        Para para = new Para(wiersz, kolumna);
        macierzRzadka.put(para, wartosc);
    }

    /**
     * Pobiera wartość dla danej pary (wiersz, kolumna) z macierzy.
     * @param wiersz Numer wiersza.
     * @param kolumna Numer kolumny.
     * @return Wartość dla danej pary (wiersz, kolumna).
     * @throws WyjatekMacierz Jeśli element jest poza zakresem macierzy lub nie jest ustawiony.
     */
    public double pobierzWartosc(int wiersz, int kolumna) throws WyjatekMacierz {
        if (wiersz >= rozmiarMacierzy || kolumna >= rozmiarMacierzy) {
            String errStr = String.format("Element w wierszu: %d kolumnie: %d jest poza zakresem macierzy", wiersz, kolumna);
            throw new WyjatekMacierz(errStr);
        }
        if (!czyUstawione(wiersz, kolumna)) {
            return 0.;
        }
        return macierzRzadka.get(new Para(wiersz, kolumna));
    }

    /**
     * Pobiera wartości wiersza o danym numerze.
     * @param wiersz Numer wiersza.
     * @return Lista wartości wiersza.
     */
    public ArrayList<Double> pobierzWiersz(int wiersz) {
        ArrayList<Para> wartosciWiersza = new ArrayList<>();

        for (Para para : macierzRzadka.keySet()) {
            if (para.wiersz == wiersz) {
                wartosciWiersza.add(para);
            }
        }

        ArrayList<Double> wartosciWierszaZeZerami = new ArrayList<>();
        for (int i = 0; i < rozmiarMacierzy; i++) {
            double aktualnaWartoscKolumny = 0.0;
            for(Para para: wartosciWiersza) {
                if (para.kolumna == i) {
                    aktualnaWartoscKolumny = macierzRzadka.get(para);
                }
            }
            wartosciWierszaZeZerami.add(aktualnaWartoscKolumny);
        }

        return wartosciWierszaZeZerami;
    }

    /**
     * Pobiera wartości kolumny o danym numerze.
     * @param kolumna Numer kolumny.
     * @return Lista wartości kolumny.
     */
    public ArrayList<Double> pobierzKolumne(int kolumna) {
        ArrayList<Para> wartosciKolumny = new ArrayList<>();

        for (Para para : macierzRzadka.keySet()) {
            if (para.kolumna == kolumna) {
                wartosciKolumny.add(para);
            }
        }

        ArrayList<Double> wartosciKolumnyZeZerami = new ArrayList<>();
        for (int i = 0; i < rozmiarMacierzy; i++) {
            double aktualnaWartoscWiersza = 0.0;
            for (Para para : wartosciKolumny) {
                if (para.wiersz == i) {
                    aktualnaWartoscWiersza = macierzRzadka.get(para);
                }
            }
            wartosciKolumnyZeZerami.add(aktualnaWartoscWiersza);
        }

        return wartosciKolumnyZeZerami;
    }

    @Override
    public String toString() {
        String macierzStr = "";
        for (int i = 0; i < rozmiarMacierzy; i++) {
            macierzStr += pobierzWiersz(i) + "\n";
        }

        return macierzStr;
    }
}