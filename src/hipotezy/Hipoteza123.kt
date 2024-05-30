package hipotezy

import rozwiazania.DlugoscKrzywej
import rozwiazania.Pola
import zapiszDoPliku
import kotlin.math.PI

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