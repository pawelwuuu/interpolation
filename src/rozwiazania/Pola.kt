package rozwiazania

import metodyCalkowania.MetodaSimpsona
import zapiszDoPliku
import kotlin.math.sin

class Pola {
    val dx = 100000.0
    fun obliczPoleKola() : Double {
//        polowa kola
        val f: (Double) -> (Double) = { Math.sqrt(-1 * it*it + 2 * it) }

        val pole = MetodaSimpsona(dx ,0.0,2.0,f).obliczPole()

        return pole * 2
    }

    fun obliczPoleParaboli() : Double {
        val f: (Double) -> (Double) = {
            -1*it*it + 2*it + 3
        }

        return MetodaSimpsona(dx, 0.0,1.0, f).obliczPole()
    }

    fun obliczPoleElipsy(a : Double, b : Double) : Double {
        val f: (Double) -> (Double) = { x ->
            Math.sqrt(b*b - (x*x*b*b)/(a*a))
        }

        return 4 * MetodaSimpsona(dx, 0.0,a, f).obliczPole()
    }

    fun obliczPoleSinusa() : Double{
        val f: (Double) -> (Double) = {
            sin(it)
        }

        return MetodaSimpsona(dx, 0.0, Math.PI, f).obliczPole()
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