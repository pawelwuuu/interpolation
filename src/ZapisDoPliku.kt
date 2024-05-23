import java.io.File

val folder = "wyniki"
fun zapiszDoPliku(nazwaPliku: String, zawartosc: String) {
    if (!File(folder).exists()) {
        File(folder).mkdir()
    }

    File("$folder/$nazwaPliku.txt").writeText(zawartosc)
}