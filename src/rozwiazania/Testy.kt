package rozwiazania

import metodyCalkowania.*
import zapiszDoPliku
import kotlin.math.abs
import kotlin.math.sin

class Testy {
    val xp = 0.0
    val xk = 2.0

    val funkcja1: (Double) -> Double = { it * it }
    val funkcja2: (Double) -> Double = { it*7 + 4 }
    val funkcja3: (Double) -> Double = { sin(it) }
    val funkcja4: (Double) -> Double = { Math.pow(it,3.0) + Math.pow(it, 2.0) + 4 * it + 4}

    val fucnkje = arrayListOf(funkcja1, funkcja2, funkcja3, funkcja4)
//    wyniki dla kazdej funkcji w przedziale od 0 do 2
    val wyniki: ArrayList<Double> = arrayListOf(
        8.0/3.0,
        22.0,
        1.41614683655,
        68.0/3.0
    )

    fun testFunkcji() {
        var logTestu = "algorytm;funkcja;dokladnosc;blad\n"
        val iterator = fucnkje.iterator()
        val dokladnosci = arrayListOf(1000.0, 100000.0, 1000000.0)

        for ((index, funkcja) in iterator.withIndex()) {
            for (dokladnosc in dokladnosci) {
                val r1 = MetodaProstokatow(dokladnosc, xp, xk, funkcja).obliczPole()
                val r2 = MetodaTrapezow(dokladnosc, xp, xk, funkcja).obliczPole()
                val r3 = MetodaSimpsona(dokladnosc, xp, xk, funkcja).obliczPole()
                val r4 = AlgorytmCSI().obliczPole(xp, xk, 600, dokladnosc, funkcja)

                println("Błąd A1 dla funkcji ${index+1} dokladnosc $dokladnosc: ${abs(wyniki[index] - r1)}")
                println("Błąd A2 dla funkcji ${index+1} dokladnosc $dokladnosc: ${abs(wyniki[index] - r2)}")
                println("Błąd A3 dla funkcji ${index+1} dokladnosc $dokladnosc: ${abs(wyniki[index] - r3)}")
                println("Błąd A4 dla funkcji ${index+1} dokladnosc $dokladnosc: ${abs(wyniki[index] - r4)}")
                println("--------------------------------")
                logTestu += "A1;${index+1};$dokladnosc;${abs(wyniki[index] - r1)}\n"
                logTestu += "A2;${index+1};$dokladnosc;${abs(wyniki[index] - r2)}\n"
                logTestu += "A3;${index+1};$dokladnosc;${abs(wyniki[index] - r3)}\n"
                logTestu += "A4;${index+1};$dokladnosc;${abs(wyniki[index] - r4)}\n"
            }
            println("***************************")
        }

        zapiszDoPliku("testFunkcji", logTestu)
    }
}