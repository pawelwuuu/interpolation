package metodyCalkowania

class MetodaProstokatow(val dokladnosc: Double, val xp: Double, val xk: Double, val funkcja: (Double) -> Double) {
    var dx : Double = 0.0
    init {
        dx = (this.xk - xp) / dokladnosc
    }

    fun obliczPole() : Double {
        var wynik = 0.0

        var i = 1
        while (i <= dokladnosc) {
            wynik += funkcja(xp + i * dx)

            i++
        }

        return wynik * dx
    }
}