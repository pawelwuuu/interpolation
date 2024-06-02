package project1;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static java.util.Collections.reverseOrder;
import static java.util.Collections.sort;

public class Project1 {

    public static void main(String[] args) {
   //     int[] n = {10,100,1000,10000,1000000};
 //       for(int i = 0; i < 5 ; i++) {
  //          double obwod = obliczObwod(n[i]);
//            System.out.println("Monte carlo pi approximation  " + approximatePi(n[i]));
//            System.out.println("circumference pi approximation  " + obwod / 2);
//
//            System.out.println("H1 Różnica między obwodem a 2pi " + (obwod - (2 * Math.PI)));
//            System.out.println("\n");
  //      }


    }

    public static double obliczObwod(int n) {
        double theta = 2 * Math.PI / n; // kąt między kolejnymi wierzchołkami wielokąta
        double obwod = 0;
        Point2D.Double sum = new Point2D.Double();
        List<Double> Plusx = new ArrayList<>();
        List<Double> Minusx = new ArrayList<>();
        List<Double> Plusy = new ArrayList<>();
        List<Double> Minusy = new ArrayList<>();
        double sumPlusx = 0;
        double sumMinusx = 0;
        double sumPlusy = 0;
        double sumMinusy = 0;
        double sumX = 0;
        double sumY = 0;


        Point2D.Double w0 = new Point2D.Double(Math.cos(theta) - 1, Math.sin(theta));
        Point2D.Double previous = w0;
        obwod += Math.sqrt((w0.x * w0.x )+ (w0.y * w0.y));
        //System.out.println(obwod);

        for (int i = 1; i < n; i++) {
            Point2D.Double current = new Point2D.Double();
            current.x = previous.x * Math.cos(theta) - previous.y * Math.sin(theta);
            current.y = previous.x * Math.sin(theta) + previous.y * Math.cos(theta);
            obwod += Math.sqrt((current.x * current.x )+ (current.y * current.y));
            //System.out.println(Math.sqrt((current.x * current.x )+ (current.y * current.y)));

            //H3
            if(current.x < 0) Plusx.add(current.x);
            else Minusx.add(current.x);
            if(current.y < 0) Plusy.add(current.y);
            else Minusy.add(current.y);


            sum.x += current.x;
            sum.y += current.y;


            previous = current;

        }
        Point2D.Double h2 = new Point2D.Double();
        h2.x = sum.x - w0.x;
        h2.y = sum.y - w0.y;
       // System.out.println("H2 różnica sumy wektorów oraz wektora zerowego wynosi  " + h2.x + "    " + h2.y);

        sort(Minusx , reverseOrder());
        sort(Plusx);
        sort(Minusy , reverseOrder());
        sort(Plusy);

        for (double liczba : Minusx) {
            sumMinusx += liczba;
        }
        for (double liczba : Plusx) {
            sumPlusx += liczba;
        }
        for (double liczba : Minusy) {
            sumMinusy += liczba;
        }
        for (double liczba : Plusy) {
            sumPlusy += liczba;
        }
        sumX = sumMinusx + sumPlusx;
        sumY = sumMinusy + sumPlusy;
       // System.out.println("H3: różnica wektora zerowego i sumy wektorów dla h3 wynosi " + (sumX - w0.x) +"   " + (sumY - w0.y));


        return obwod;
    }
    public static double approximatePi(int n) {
        int insideCircleCount = 0; // licznik punktów wewnątrz koła
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            // Losowanie współrzędnych punktu
            double x = random.nextDouble();
            double y = random.nextDouble();

            // Obliczanie odległości od środka (0,0)
            double distance = Math.sqrt(x * x + y * y);

            // Sprawdzenie, czy punkt jest wewnątrz koła
            if (distance <= 1) {
                insideCircleCount++;
            }
        }

        // Obliczanie przybliżonej wartości liczby π
        return 4.0 * insideCircleCount / n;
    }
}

