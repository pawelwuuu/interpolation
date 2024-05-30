package rozwiazania

import metodyCalkowania.AlgorytmCSI
import metodyCalkowania.MetodaProstokatow
import metodyCalkowania.MetodaSimpsona
import metodyCalkowania.MetodaTrapezow
import zapiszDoPliku
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sin

class Pola {
    var dx = 100000.0
    val iloscPrzedzialow = 500
    fun obliczPoleKola() : ArrayList<Double> {

//        polowa kola
        val f: (Double) -> (Double) = { Math.sqrt(-1 * it*it + 2 * it) }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, 0.0, 2.0, f).obliczPole() * 2
        )
        wyniki.add(
            MetodaTrapezow(dx, 0.0, 2.0, f).obliczPole() * 2
        )

        wyniki.add(
            MetodaSimpsona(dx ,0.0,2.0,f).obliczPole() * 2
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(0.0, 2.0, iloscPrzedzialow, dx, f) * 2
        )

        return wyniki
    }

    fun obliczPoleParaboli() : ArrayList<Double> {
        val f: (Double) -> (Double) = {
            it*it + 2*it + 3
        }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, 0.0, 1.0, f).obliczPole()
        )
        wyniki.add(
            MetodaTrapezow(dx, 0.0, 1.0, f).obliczPole()
        )

        wyniki.add(
            MetodaSimpsona(dx ,0.0,1.0,f).obliczPole()
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(0.0, 1.0, iloscPrzedzialow, dx, f)
        )

        return wyniki
    }

    fun obliczPoleElipsy(a : Double, b : Double) : ArrayList<Double> {
        val f: (Double) -> (Double) = { x ->
            Math.sqrt(abs(b*b - (x*x*b*b)/(a*a)))
        }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, 0.0, a, f).obliczPole() * 4
        )
        wyniki.add(
            MetodaTrapezow(dx, 0.0, a, f).obliczPole() * 4
        )

        wyniki.add(
            MetodaSimpsona(dx ,0.0,a,f).obliczPole() * 4
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(0.0, a, iloscPrzedzialow, dx, f) * 4
        )

        return wyniki
    }

    fun obliczPoleSinusa() : ArrayList<Double>{
        val f: (Double) -> (Double) = {
            sin(it)
        }

        val wyniki = ArrayList<Double>()
        wyniki.add(
            MetodaProstokatow(dx, 0.0, PI, f).obliczPole()
        )
        wyniki.add(
            MetodaTrapezow(dx, 0.0, PI, f).obliczPole()
        )

        wyniki.add(
            MetodaSimpsona(dx ,0.0,PI,f).obliczPole()
        )

        wyniki.add(
            AlgorytmCSI().obliczPole(0.0, PI, iloscPrzedzialow, dx, f)
        )

        return wyniki
    }

    fun obliczPolaElipsy() : String {
        var str = "a b pole\n"
        val parametryElipsy: ArrayList<Pair<Double, Double>> = arrayListOf(
            Pair(1.0, 1.0),
            Pair(3.0, 2.0),
            Pair(5.0, 7.0),
            Pair(15.0, 6.0),
        )

        for (parametry in parametryElipsy) {
            str += "${parametry.first} ${parametry.second} ${obliczPoleElipsy(parametry.first, parametry.second)}\n"
        }

        return str
    }

    fun zapiszWynikiDoPlikow() {
        zapiszDoPliku("poleKola", obliczPoleKola().toString())
        zapiszDoPliku("poleParaboli", obliczPoleParaboli().toString())
        zapiszDoPliku("poleSinusa", obliczPoleSinusa().toString())
        zapiszDoPliku("poleElipsy", obliczPolaElipsy())
    }


}