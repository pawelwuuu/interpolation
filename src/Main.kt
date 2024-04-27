import metodyCalkowania.MetodaFunkcjiSklejanych
import metodyCalkowania.MetodaProstokatow
import metodyCalkowania.MetodaSimpsona
import metodyCalkowania.MetodaTrapezow

fun main() {
//    val wynik = MetodaTrapezow(10000000.0, 0.0, 3.14) {
//        Math.sin(it)
//    }.obliczPole()
//
//
//    val wynik2 = MetodaProstokatow(10000000.0, 0.0, 3.14) {
//        Math.sin(it)
//    }.obliczPole()
//
//    val wynik3 = MetodaSimpsona(10000000.0, 0.0, 3.14) {
//        Math.sin(it)
//    }.obliczPole()
//
//
//    println(wynik)
//    println(wynik2)
//    println(wynik3)
    val metodaFunkcjiSklejanych = MetodaFunkcjiSklejanych()

    metodaFunkcjiSklejanych.dodajParametry(1.0,12.0)
    metodaFunkcjiSklejanych.dodajParametry(5.0,-26.0)
    metodaFunkcjiSklejanych.dodajParametry(8.0,-14.0)
    metodaFunkcjiSklejanych.dodajParametry(10.0,37.0)

    metodaFunkcjiSklejanych.obliczRownaniax3()
    metodaFunkcjiSklejanych.obliczRownaniax2()
    metodaFunkcjiSklejanych.obliczRownaniax1()
    metodaFunkcjiSklejanych.obliczRownaniax1DoZeraDla1iN()
    metodaFunkcjiSklejanych.stworzMacierz()

    println(metodaFunkcjiSklejanych.macierzRzadka)
//    println(metodaFunkcjiSklejanych)


    WyswietlaczTablic.wyswietlTablice(metodaFunkcjiSklejanych.wynikowe.toDoubleArray())
    WyswietlaczTablic.wyswietlTablice(GaussEliminacja.rozwiaz(metodaFunkcjiSklejanych.macierzRzadka, metodaFunkcjiSklejanych.wynikowe.toDoubleArray()))
}