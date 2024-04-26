package metodyCalkowania

class MetodaTrapezow(val dokladnosc: Double, val xp: Double, val xk: Double, val funkcja: (Double) -> Double) {
    var dx : Double = 0.0
    init {
        dx = (this.xk - xp) / dokladnosc
    }

    fun obliczPole() : Double {
        var wynik = 0.0

        var i = 1.0


        while (i < dokladnosc - 1) {
            wynik += funkcja(xp + i * dx)

            i++
        }

        wynik = (wynik + (funkcja(xp) - funkcja(xk) / 2)) * dx

        return wynik
    }

}