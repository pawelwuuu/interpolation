package rozwiazania

import metodyCalkowania.AlgorytmCSI
import metodyCalkowania.MetodaProstokatow
import metodyCalkowania.MetodaSimpsona
import metodyCalkowania.MetodaTrapezow
import rozwiazania.NumericalIntegration.rectangleRule
import zapiszDoPliku

class DlugoscKrzywej {
    var dx = 10000.0
    val iloscPrzedzialow = 100

    // Funkcja obliczająca pochodną numerycznie
    fun numericalDerivative(f: (Double) -> Double, x: Double, h: Double): Double {
        return (f(x + h) - f(x - h)) / (2 * h)
    }

    // Funkcja długości krzywej
    fun dlugoscKrzywejKola(f: (Double) -> Double): ArrayList<Double> {
        // Obliczanie pochodnej funkcji f
        val derivative = { x: Double -> numericalDerivative(f, x, 1e-10) }
        // Funkcja podcałkowa sqrt(1 + (f'(x))^2)
        val integrand = { x: Double -> kotlin.math.sqrt(1 + Math.pow(derivative(x), 2.0)) }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, -1.0, 1.0, integrand).obliczPole() * 2
        )
        wyniki.add(
            MetodaTrapezow(dx, -1.0, 1.0, integrand).obliczPole() * 2
        )

        wyniki.add(
            MetodaSimpsona(dx ,-1.0, 1.0,integrand).obliczPole() * 2
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(-1.0, 1.0, iloscPrzedzialow, dx, integrand) * 2
        )

        return wyniki
    }

    fun dlugoscKrzywejElipsy(a: Double, f: (Double) -> Double): ArrayList<Double> {
        // Obliczanie pochodnej funkcji f
        val derivative = { x: Double -> numericalDerivative(f, x, 1e-10) }
        // Funkcja podcałkowa sqrt(1 + (f'(x))^2)
        val integrand = { x: Double -> kotlin.math.sqrt(1 + Math.pow(derivative(x), 2.0)) }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, -1 * a, a, integrand).obliczPole() * 2
        )
        wyniki.add(
            MetodaTrapezow(dx, -1 * a, a, integrand).obliczPole() * 2
        )

        wyniki.add(
            MetodaSimpsona(dx ,-1 * a, a,integrand).obliczPole() * 2
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(-1 * a, a, iloscPrzedzialow, dx, integrand) * 2
        )

        return wyniki
    }

    fun main() {
        val r = 1

        // 1. Obwód koła o promieniu 1, używając parametrów (x = cos(t), y = sin(t))
        val circleIntegrand = { t: Double -> kotlin.math.sqrt(kotlin.math.abs(r - (t * t))) }
        val circleCircumference = dlugoscKrzywejKola(circleIntegrand)
        println(circleCircumference)




//
        val a = 1.0 // półoś "a"
        val b = 1 // półoś "b"

        val ellipseIntegrand = { t: Double -> b / a * kotlin.math.sqrt(kotlin.math.abs(a * a - t * t)) }
        val elypseCircumference = dlugoscKrzywejElipsy(a, ellipseIntegrand)
        println(elypseCircumference)
//
//        val sinIntegrand = { t: Double -> kotlin.math.sin(t) }
//        val sinCircumference = curveLength(sinIntegrand, 0.0, 2 * kotlin.math.PI, n, "simpson")
//        println("Obwód elipsy (metodą Simpsona): $sinCircumference")
    }


}