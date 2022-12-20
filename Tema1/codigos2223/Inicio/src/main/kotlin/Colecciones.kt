class Colecciones {
    fun main(arg: Array<String>) {
        var java = Lenguaje("java", "multi", 18)
        var kotlin = Lenguaje( nombre="Kotlin", plataforma = "multi")
        var cSharp = Lenguaje ("C#");
        println(java.plataforma);
        println(kotlin.version);
        println(cSharp.plataforma)
    }

    class Lenguaje (var nombre: String){
        var plataforma: String? = null;
        var version: Int? = null;

        constructor(nombre: String, plataforma: String, version: Int): this(nombre) {
            this.plataforma = plataforma;
            this.version = version;
        }

        constructor(nombre: String, plataforma: String): this(nombre){
            this.plataforma = plataforma;
        }
    }
}