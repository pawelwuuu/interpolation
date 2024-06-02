package hipotezy

import rozwiazania.DlugoscKrzywej
import rozwiazania.Pola
import zapiszDoPliku
import kotlin.math.PI
import kotlin.random.Random

fun obliczHipoteze123() {
    var str = ""
    val pola = Pola()
    for (i in 1..8) {
        str += "pola dla dx: ${Math.pow(10.0, i.toDouble())}\n"
        pola.dx = Math.pow(10.0, i.toDouble())
        str += pola.obliczPoleKola().map {
            it - PI
        }.toString() + "\n"
    }
    zapiszDoPliku("hipotezaPole", str)
    str = ""

    val dlugoscKrzywej = DlugoscKrzywej()
    for (i in 1..8) {
        str += "obwody dla dx: ${Math.pow(10.0, i.toDouble())}\n"
        dlugoscKrzywej.dx = Math.pow(10.0, i.toDouble())
        str += dlugoscKrzywej.dlugoscKrzywejKola{ t: Double -> kotlin.math.sqrt(kotlin.math.abs(1.0 - (t * t))) }.map {
            it - 2*PI
        }.toString() + "\n"
    }
    zapiszDoPliku("hipotezaObwody", str)

}

fun hipoteza2() {
    for (i in 0..10) {
        val a = Random.nextDouble(0.0, 10.0) // Generuje losową liczbę Double od 0.0 do 10.0
        val b = Random.nextDouble(0.0, 10.0) // Generuje losową liczbę Double od 0.0 do 10.0
        val wyniki = Pola().obliczPoleElipsy(a, b)

        val poleElipsy = PI * a * b

        val skorygowaneWyniki = wyniki.map { wynik ->
            wynik - poleElipsy
        }

        println("a: $a b: $b  Wyniki:  $skorygowaneWyniki")
    }
}