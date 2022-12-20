fun main(arguments: Array<String>) {
    var arrayCosas = arrayOf(1,2,3,4,5,6,true);
    var arrayNumeros = intArrayOf(1,2,3,4,5,6,7);
    for (cosa in arrayCosas) {
        println(cosa);
    }
    arrayNumeros.forEach { it -> print(it) }
    arrayNumeros.forEachIndexed({index: Int, it:Int->println("$index - $it")})

    var lenguajes = arrayOf("Java", "Kotlin", "C#", "Python", "JavaScript", "PowerScript");
    var encontrado = lenguajes.find { elemento: String -> elemento.contains("Script") }
    println(encontrado)
}
fun tiposArrays(){
    var arrayEmpty = emptyArray<String>();
    var arrayNull = arrayOfNulls<String>(5);
    var arrayCosas = arrayOf(1,2,3,4,5,6,true);
    var arrayNumeros = intArrayOf(1,2,3,4,5,6,7);
    var arrayInicializado = Array<Int>(6) { (it + 1) };
}
