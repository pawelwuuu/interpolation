import metodyCalkowania.*
import rozwiazania.Testy
import kotlin.math.sin

fun main() {
    val wynik = MetodaTrapezow(100000.0, 1.0, 3.0) {
        Math.sin(it)
    }.obliczPole()


    val wynik2 = MetodaProstokatow(100000.0, 1.0, 3.0) {
        Math.sin(it)
    }.obliczPole()

    val wynik3 = MetodaSimpsona(100000.0, 1.0, 3.0) {
        it*it
    }.obliczPole()


    println(wynik)
    println(wynik2)
    println(wynik3)

    Testy().testFunkcji()







}