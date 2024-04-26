import metodyCalkowania.MetodaProstokatow
import metodyCalkowania.MetodaSimpsona
import metodyCalkowania.MetodaTrapezow

fun main() {
    val wynik = MetodaTrapezow(10000000.0, 0.0, 3.14) {
        Math.sin(it)
    }.obliczPole()


    val wynik2 = MetodaProstokatow(10000000.0, 0.0, 3.14) {
        Math.sin(it)
    }.obliczPole()

    val wynik3 = MetodaSimpsona(10000000.0, 0.0, 3.14) {
        Math.sin(it)
    }.obliczPole()


    println(wynik)
    println(wynik2)
    println(wynik3)
}