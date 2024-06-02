package rozwiazania;

import java.util.function.Function;

public class NumericalIntegration {

    // Funkcja obliczająca całkę metodą prostokątów
    public static double rectangleRule(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += f.apply(a + i * h);
        }
        return h * sum;
    }

    // Funkcja obliczająca całkę metodą trapezów
    public static double trapezoidalRule(Function<Double, Double> f, double a, double b, int n) {
        double h = (b - a) / n;
        double sum = 0.5 * (f.apply(a) + f.apply(b));
        for (int i = 1; i < n; i++) {
            sum += f.apply(a + i * h);
        }
        return h * sum;
    }

    // Funkcja obliczająca całkę metodą Simpsona
    public static double simpsonRule(Function<Double, Double> f, double a, double b, int n) {
        if (n % 2 != 0) {
            n++; // n musi być parzyste
        }
        double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);
        for (int i = 1; i < n; i += 2) {
            sum += 4 * f.apply(a + i * h);
        }
        for (int i = 2; i < n; i += 2) {
            sum += 2 * f.apply(a + i * h);
        }
        return (h / 3) * sum;
    }

    // Funkcja obliczająca pochodną numerycznie
    public static double numericalDerivative(Function<Double, Double> f, double x, double h) {
        return (f.apply(x + h) - f.apply(x - h)) / (2 * h);
    }

    // Funkcja długości krzywej
    public static double curveLength(Function<Double, Double> f, double a, double b, int n, String method) {
        // Obliczanie pochodnej funkcji f
        Function<Double, Double> derivative = x -> numericalDerivative(f, x, 1e-5);
        // Funkcja podcałkowa sqrt(1 + (f'(x))^2)
        Function<Double, Double> integrand = x -> Math.sqrt(1 + Math.pow(derivative.apply(x), 2));

        // Wybór metody numerycznej
        switch (method) {
            case "rectangle":
                return rectangleRule(integrand, a, b, n);
            case "trapezoidal":
                return trapezoidalRule(integrand, a, b, n);
            case "simpson":
                return simpsonRule(integrand, a, b, n);
            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }
    }

    public static void main(String[] args) {
        int n = 1000000;  // Liczba podziałów
        int r = 1;

        // 1. Obwód koła o promieniu 1, używając parametrów (x = cos(t), y = sin(t))
        Function<Double, Double> circleIntegrand = t -> Math.sqrt(Math.abs(r-(t*t)));
        double circleCircumference = curveLength(circleIntegrand, -1, 1, n,"simpson");
        System.out.println("Obwód koła (metodą Simpsona): " + 2 * circleCircumference);
        

        int a = 1; // półoś "a"
        int b = 1; // półoś "b"

        Function<Double, Double> ellipseIntegrand = t -> b/a * Math.sqrt(Math.abs(a*a - t * t));
        double ElypseCircumference = curveLength(ellipseIntegrand, -a, a, n,"simpson");
        System.out.println("Obwód elipsy (metodą Simpsona): " + 2 * ElypseCircumference);

        Function<Double, Double> sinIntegrand = t -> Math.sin(t);
        double sinCircumference = curveLength(sinIntegrand, 0, 2*Math.PI, n,"simpson");
        System.out.println("Obwód elipsy (metodą Simpsona): " + sinCircumference);
    }
}