package metodyCalkowania

class MetodaSimpsona(val dokladnosc: Double, val xp: Double, val xk: Double, val funkcja: (Double) -> Double) {
    var dx : Double = 0.0
    init {
        dx = (this.xk - xp) / dokladnosc
    }

    fun obliczPole() : Double {
        var wynik = 0.0
        var s = 0.0
        var x = 0.0

        var i = 1
        while (i < dokladnosc) {
            x = xp + i * dx
            s += funkcja(x-dx/2)
            wynik += funkcja(x)

            i++
        }
        s+= funkcja(xk - dx / 2)
        wynik = (dx/6) * (funkcja(xp) + funkcja(xk) + 2*wynik + 4*s)

        return wynik
    }

}