package metodyCalkowania

import GaussEliminacja
import MacierzRzadka.MacierzRzadka

class AlgorytmCSI {
    private val punkty = ArrayList<Pair<Double, Double>>()
    private var macierzRzadka: MacierzRzadka? = null
    private var wartosciM = ArrayList<Double>()
    private val parametryFunkcji = ArrayList<ArrayList<Double>>()

    private fun dodajPunkt(x : Double, y: Double) {
        punkty.add(Pair(x,y))
    }

    private fun obliczWartosciM() {
        macierzRzadka = MacierzRzadka(punkty.size)
        val wektorWynikowy: ArrayList<Double> = arrayListOf()

        macierzRzadka!!.ustawWartosc(0,0,2.0)
        macierzRzadka!!.ustawWartosc(punkty.size-1,punkty.size-1,2.0)
        wektorWynikowy.add(0.0)

        for (j in 1 until punkty.size - 1) {
            val hj = punkty[j].first - punkty[j-1].first
            val hj1 = punkty[j+1].first - punkty[j].first
            val mi = hj / (hj+hj1)
            val alfa = hj1/ (hj+hj1)
            val sigma = 6 / (hj + hj1) * ((punkty[j+1].second - punkty[j].second) / hj1 - (punkty[j].second - punkty[j-1].second)/ hj)

            macierzRzadka!!.ustawWartosc(j, j-1, mi)
            macierzRzadka!!.ustawWartosc(j, j, 2.0)
            macierzRzadka!!.ustawWartosc(j, j+1, alfa)
            wektorWynikowy.add(sigma)
        }
        wektorWynikowy.add(0.0)

        val rozwiazanie = GaussEliminacja.rozwiaz(macierzRzadka, wektorWynikowy.toDoubleArray())
        rozwiazanie.forEach {
            wartosciM.add(it)
        }
    }

    private fun obliczParametryFunkcji() {
        for (j in 0..<punkty.size - 1) {
            val parametry = ArrayList<Double>()

            val hj1 = punkty[j+1].first - punkty[j].first
            val b = (punkty[j+1].second - punkty[j].second)/hj1 - (2* wartosciM[j] + wartosciM[j+1])/6 * hj1
            val c = wartosciM[j]/2
            val d = (wartosciM[j+1] - wartosciM[j])/(6*hj1)

            parametry.add(punkty[j].second)
            parametry.add(b)
            parametry.add(c)
            parametry.add(d)

            parametryFunkcji.add(parametry)
        }
    }

    private fun obliczWartoscX(x: Double): Double {
        var numerFunkcji = -1
        for(i in 0 until punkty.size-1){
            if (x >= punkty[i].first && x <= punkty[i+1].first){
                numerFunkcji = i
                break
            }
        }
        val parametryFunkcji = parametryFunkcji[numerFunkcji]
        val xj = punkty[numerFunkcji].first

        return parametryFunkcji[0] + parametryFunkcji[1] * (x - xj) + parametryFunkcji[2] * (x - xj) * (x - xj) + parametryFunkcji[3] * (x - xj) * (x - xj) * (x - xj)
    }

    private fun dodajPunktyPrzedzial(p: Double, k: Double, iloscPodzialow: Int, f: (Double) -> (Double)) {
        val z = (k-p) / iloscPodzialow
        var pNowe = p
        do {
            dodajPunkt(pNowe, f(pNowe))
            pNowe += z
        } while(pNowe <= k)

        if (k != punkty.last.first) {
            dodajPunkt(k, f(k))
        }
    }

    fun obliczPole(p: Double, k: Double, iloscPodzialow: Int, dokladnosc: Double, f: (Double) -> (Double)) : Double {
        dodajPunktyPrzedzial(p, k, iloscPodzialow, f)
        obliczWartosciM()
        obliczParametryFunkcji()

        var suma = 0.0
        var i = p
        while (i < k) {
            suma+= obliczWartoscX(i)
            i+= 1/dokladnosc
        }

        return suma / dokladnosc
    }



}