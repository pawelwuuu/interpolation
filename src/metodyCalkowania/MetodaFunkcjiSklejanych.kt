package metodyCalkowania

import MacierzRzadka.MacierzRzadka

class MetodaFunkcjiSklejanych {
    class Punkt(val x: Double, val y: Double)

    var macierzRzadka : MacierzRzadka? = null
    var rozmiarMacierzy = 0
    var funkcjeSklejane = 0
    var iloscRownanx3 = 0
    var iloscRownanx2 = 0
    var iloscRownanx1 = 0
    val punktyFunkcji = ArrayList<Punkt>()

    val rownaniax3 = ArrayList<ArrayList<Double>>()
    val rownaniax2 = ArrayList<ArrayList<Double>>()
    val rownaniax1 = ArrayList<ArrayList<Double>>()
    val rownaniaDoZera = ArrayList<ArrayList<Double>>()
    val wynikowe = ArrayList<Double>()



    public fun dodajParametry(x: Double, y: Double) {
        punktyFunkcji.add(Punkt(x,y))
        funkcjeSklejane = punktyFunkcji.size - 1
        iloscRownanx3 = funkcjeSklejane * 2
        iloscRownanx2 = funkcjeSklejane - 1
        iloscRownanx1 = funkcjeSklejane - 1

        rozmiarMacierzy = iloscRownanx1 + iloscRownanx2 + iloscRownanx3 + 2
    }

    public fun obliczRownaniax3() {
        for (i in 0 until punktyFunkcji.size) {
            val punkt = punktyFunkcji[i]
            val wyrazyWolne = ArrayList<Double>()
            wyrazyWolne.add(Math.pow(punkt.x, 3.0))
            wyrazyWolne.add(Math.pow(punkt.x, 2.0))
            wyrazyWolne.add(punkt.x)
            wyrazyWolne.add(1.0)

            if (i == 0 || i == punktyFunkcji.size - 1) {
                rownaniax3.add(wyrazyWolne)
                wynikowe.add(punkt.y)
            } else {
                rownaniax3.add(wyrazyWolne)
                rownaniax3.add(wyrazyWolne)
                wynikowe.add(punkt.y)
                wynikowe.add(punkt.y)
            }
        }
    }

    public fun obliczRownaniax2() {
        for (i in 1 until punktyFunkcji.size - 1) {
            val punkt = punktyFunkcji[i]
            val wyrazyWolne1 = ArrayList<Double>()
            val wyrazyWolne2 = ArrayList<Double>()

            wyrazyWolne1.add(3 * Math.pow(punkt.x, 2.0))
            wyrazyWolne1.add(2 * punkt.x)
            wyrazyWolne1.add(1.0)

            wyrazyWolne2.add(-3 * Math.pow(punkt.x, 2.0))
            wyrazyWolne2.add(-2 * punkt.x)
            wyrazyWolne2.add(-1.0)


            rownaniax2.add(wyrazyWolne1)
            rownaniax2.add(wyrazyWolne2)
            wynikowe.add(0.0)
        }
    }

    fun obliczRownaniax1DoZeraDla1iN() {
        rownaniaDoZera.add(
            arrayListOf(
                6 * punktyFunkcji[0].x,
                2.0
            )
        )
        rownaniaDoZera.add(
            arrayListOf(
                6 * punktyFunkcji[punktyFunkcji.size - 1].x,
                2.0
            )
        )

        wynikowe.add(0.0)
        wynikowe.add(0.0)
    }

    public fun obliczRownaniax1() {
        for (i in 1 until punktyFunkcji.size - 1) {
            val punkt = punktyFunkcji[i]
            val wyrazyWolne1 = ArrayList<Double>()
            val wyrazyWolne2 = ArrayList<Double>()

            wyrazyWolne1.add(6 * punkt.x)
            wyrazyWolne1.add(2.0)

            wyrazyWolne2.add(-6 * punkt.x)
            wyrazyWolne2.add(-2.0)


            rownaniax1.add(wyrazyWolne1)
            rownaniax1.add(wyrazyWolne2)
            wynikowe.add(0.0)
        }
    }

    public fun stworzMacierz() {
        macierzRzadka = MacierzRzadka(rozmiarMacierzy)

        var przesuniecie = 0
        //wpisanie rownan x3
        for (i in 0 until rownaniax3.size) {
            macierzRzadka!!.ustawWartosc(i, przesuniecie, rownaniax3[i][0])
            macierzRzadka!!.ustawWartosc(i, przesuniecie + 1, rownaniax3[i][1])
            macierzRzadka!!.ustawWartosc(i, przesuniecie + 2, rownaniax3[i][2])
            macierzRzadka!!.ustawWartosc(i, przesuniecie + 3, rownaniax3[i][3])

            if (i % 2 != 0) {
                przesuniecie += 4
            }
        }

        var wiersz = iloscRownanx3
        przesuniecie = 0
        //wpisanie rownan x2
        var i = 0
        while (i <= rownaniax2.size / 2) {
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie, rownaniax2[i][0])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 1, rownaniax2[i][1])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 2, rownaniax2[i][2])

            przesuniecie += 4
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie, rownaniax2[i + 1][0])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 1, rownaniax2[i + 1][1])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 2, rownaniax2[i + 1][2])

            i += 2
            wiersz+=1
        }

        wiersz = iloscRownanx3 + iloscRownanx2
        przesuniecie = 0
        //wpisanie rownan x1
        i = 0
        while (i <= rownaniax1.size / 2) {
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie, rownaniax1[i][0])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 1, rownaniax1[i][1])

            przesuniecie += 4
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie, rownaniax1[i + 1][0])
            macierzRzadka!!.ustawWartosc(wiersz, przesuniecie + 1, rownaniax1[i + 1][1])

            i += 2
            wiersz+=1
        }

        //wpisanie rownan do zera
        wiersz = iloscRownanx3 + iloscRownanx2 + iloscRownanx1
        macierzRzadka!!.ustawWartosc(wiersz, 0, 6 * punktyFunkcji[0].x)
        macierzRzadka!!.ustawWartosc(wiersz, 1, 2.0)

        macierzRzadka!!.ustawWartosc(wiersz + 1, rozmiarMacierzy - 4, 6 * punktyFunkcji.last().x)
        macierzRzadka!!.ustawWartosc(wiersz + 1, rozmiarMacierzy - 3, 2.0)
    }



    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.appendln("MetodaFunkcjiSklejanych:")
        stringBuilder.appendln("Ilość równań x^3: $iloscRownanx3")
        stringBuilder.appendln("Ilość równań x^2: $iloscRownanx2")
        stringBuilder.appendln("Ilość równań x^1: $iloscRownanx1")
        stringBuilder.appendln("Punkty funkcji:")
        for (punkt in punktyFunkcji) {
            stringBuilder.appendln("x: ${punkt.x}, y: ${punkt.y}")
        }
        stringBuilder.appendln("Równania x^3:")
        for (rownanie in rownaniax3) {
            stringBuilder.appendln(rownanie.joinToString(prefix = "[", postfix = "]", separator = ", "))
        }
        stringBuilder.appendln("Równania x^2:")
        for (rownanie in rownaniax2) {
            stringBuilder.appendln(rownanie.joinToString(prefix = "[", postfix = "]", separator = ", "))
        }
        stringBuilder.appendln("Równania x^1:")
        for (rownanie in rownaniax1) {
            stringBuilder.appendln(rownanie.joinToString(prefix = "[", postfix = "]", separator = ", "))
        }
        stringBuilder.appendln("Wynikowe:")
        stringBuilder.appendln(wynikowe.joinToString(prefix = "[", postfix = "]", separator = ", "))
        return stringBuilder.toString()
    }

}