import MacierzRzadka.WyjatekMacierz;
import MacierzRzadka.MacierzRzadka;

import java.util.ArrayList;

public class GaussEliminacja {
    public static double[] rozwiaz(MacierzRzadka macierzRzadka, double[] b) {
        int n = b.length;
        double[] x = new double[n];

        // Eliminacja Gaussa z częściowym wyborem
        for (int k = 0; k < n - 1; k++) {
            int maxIndex = k;
            double maxVal = Math.abs(macierzRzadka.pobierzWartosc(k,k));
            for (int i = k + 1; i < n; i++) {
                if (Math.abs(macierzRzadka.pobierzWartosc(i,k)) > maxVal) {
                    maxVal = Math.abs(macierzRzadka.pobierzWartosc(i,k));
                    maxIndex = i;
                }
            }
            // Zamiana wierszy k i maxIndex w macierzy A oraz w wektorze b
            ArrayList<Double> temp = macierzRzadka.pobierzWiersz(k);

            ArrayList<Double> wierszMaxIndex = macierzRzadka.pobierzWiersz(maxIndex);
            for (int i = 0; i < wierszMaxIndex.size(); i++) {
                macierzRzadka.ustawWartosc(k,i, wierszMaxIndex.get(i));
            }

            for (int i = 0; i < temp.size(); i++) {
                macierzRzadka.ustawWartosc(maxIndex,i, temp.get(i));
            }

            double tempB = b[k];
            b[k] = b[maxIndex];
            b[maxIndex] = tempB;

            for (int i = k + 1; i < n; i++) {
                double m = macierzRzadka.pobierzWartosc(i,k) / macierzRzadka.pobierzWartosc(k,k);
                for (int j = k; j < n; j++) {
                    macierzRzadka.ustawWartosc(i,j, macierzRzadka.pobierzWartosc(i,j) - m * macierzRzadka.pobierzWartosc(k,j));
                }
                b[i] -= m * b[k];
            }
        }

        // Rozwiązanie układu równań z postaci trójkątnej górnej
        for (int i = n - 1; i >= 0; i--) {
            x[i] = b[i];
            for (int j = i + 1; j < n; j++) {
                x[i] -= macierzRzadka.pobierzWartosc(i,j) * x[j];

            }
            x[i] /= macierzRzadka.pobierzWartosc(i,i);
        }

        return x;
    }
}