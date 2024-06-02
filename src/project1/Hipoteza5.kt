package project1

import metodyCalkowania.MetodaProstokatow
import metodyCalkowania.MetodaSimpsona
import metodyCalkowania.MetodaTrapezow
import kotlin.math.abs


class Hipoteza5 {
    val nArraySize = 10000
    val n = IntArray(nArraySize) { 10000 + it * 500 }
    fun hipoteza5() {
        for (j in 0 until nArraySize) {
            println("")
            print(abs(Math.PI-(Project1.obliczObwod(n[j])/ 2)) )
            print(";")
            print(abs(Math.PI-Project1.approximatePi(n[j])))
            print(";")

            for (i in 0 until 3) {
                print(abs(Math.PI-obliczPolaKola(n[j].toDouble())[i]))
                print(";")
            }
        }



    }
    fun obliczPolaKola(dx : Double) : DoubleArray {

//        polowa kola
        val f: (Double) -> (Double) = { Math.sqrt(-1 * it*it + 2 * it) }
        val pola = DoubleArray(3)

        pola[2] = 2 * MetodaSimpsona(dx ,0.0,2.0,f).obliczPole()
        pola[0] = 2 * MetodaProstokatow(dx ,0.0,2.0,f).obliczPole()
        pola[1] = 2 * MetodaTrapezow(dx ,0.0,2.0,f).obliczPole()


        return pola
    }


}