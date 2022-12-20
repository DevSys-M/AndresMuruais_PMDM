// clase - variable - funciones

// variable mutable
// var nombre:String = "valor";

var numero:Int = 2; // o var numero = 2;

// variable no mutable

// var nombre: String? = null;

var nombre: String? = null
val DNI: String = "123A"  // constante. no puede ser Null
var edad:Int = 18 // tipo complejo, se define con clases (Clases con Mayuscula), y con el Int


// Funcion main
fun main(arg: Array<String>) : Unit { // unit es lo mismo que void, va por defecto (opcional ponerlo?)
    // meter por consola
    //println("Introduce cual es tu nombre")
    //nombre = readLine()
    //println("Introduce cual es tu edad")
    //edad = readLine()!!.toInt(); // hay que obligar a no ser nulo, usaremos este metodo por ahora
    // edad = readLine() as Int // otra forma de hacer la conversion sin forzar el nulo


    // println(nombre?.length)   ? indica que puede ser nulo
    // println(nombre!!.length)  // !! forzar que va a ser nulo

    //println("Hola mi nombre es ${nombre} tengo ${edad} años") //Formato sin concatenar
    //println("La suma del numero 4 y el numero 2 es ${4 + 2}") //dentro de {} puedes meter codigo, operaciones
    //estructurasIf()
    //estructurasFor()
    // funcionArrays()
    //funcionEjercicioArrays()
    funcionArraylist()
}
fun funcionArraylist(){
    //superClase List -> no mutables y mutables(ArrayList)
    var arrayMutable = ArrayList<String?>();
}
fun funcionArrays() {
    //String[] nombre={"asdf","asd"}->length 2
    //String[] nombre= new String [2]->length 2 null,null
    //int[] nombre = new int[2] --> length 2 0,0
    var arrayNumeros: Array<Any>? = null
    var arrayCosas: Array<Int?> = arrayOfNulls<Int>(5);
    // hacen los mismo acceden a datos del array
    arrayCosas[0] = 1
    arrayCosas[1] = 2
    arrayCosas[2] = 3
    arrayCosas[3] = 4
    arrayCosas[4] = 5
    // 1,2,3,4,5
    /*
    for (i in arrayCosas){
        //operador elvis
        //if(i?:-1 >=4 ) println(i)
        if(i!! >=4 ) println(i)
    }
    */

    /*  arrayCosas.get(0)
    arrayCosas[1]= 1;
    arrayCosas.set(1,null)
    println(arrayCosas.size)
    */
    /*
    var index = 0
    arrayCosas.forEach ({ element ->
        println("Linea de ejecucion $index")
        println("Impresion del elemento $element")
        $index++
    })
     */
    //arrayCosas.forEach { e->println(e) }
    arrayCosas.forEachIndexed({index,element->
        println("Impresion $index")
        println("Elemento con valor: $element")
    })

}
fun funcionEjercicioArrays(){
    var arrayPalabras = arrayOfNulls<String>(10);
    for(i in 0 .. 9){
        println("Que palabras quieres guarar")
        var palabra = readln();
        arrayPalabras[i]= palabra
    }
    /*
    arrayPalabras.forEachIndexed({ index, value ->

        //longitud de  las palabras mayor o igual a 5
        if (value?.length!! >= 5){
            println("Poscion: $index")
            println("Elemento: $value")
        }


    })
*/
    var listafiltrada=arrayPalabras.filter ({ value -> value?.length!! >=5 })
    //it palabra reservada que funciona si solo hay un elemento.
    listafiltrada.forEach({print(it)})

}

fun estructurasWhen(){
    //switch
    //case->
    //no rango 1 y 5
    //salida de un metodo
    //switch vestido de if
    var numero: Int = 14

    when(numero) {
        1 -> {
            println("El caso es el valor 1")
        }

        in 2..10 -> {
            println("El numero esta en el rango 2..10")
        }

        !in 1..10 -> {
            println("El numero no esta en el rango")
        }
    }
}
fun estructurasIf() {
        // ejecuciones
        // no hay if ternario
        //la salida de un if la puedo guardar en una variable
        // if (c) {} else if (c){} else{}
        println("Indica por teclado un numero")
        var numero: Int=readLine()!!.toInt()
        if(numero>18)
            println("Eres mayor de edad")
        else
            println("Eres menor de edad")

        println("Introduce tu nombre")
        var nombre: String? = readLine()
        //mayoria de los casos se pon !! para garantizar el null
        if ((nombre?.length ?: 0) >= 5){
            println("Tu nombre es demasiado largo")
        }
        println(nombre)

        var valorNumerico:Int = if(nombre!!.length >5 && numero<10) 10 else 0
        println(valorNumerico)
    }
fun estructurasFor(){
    // inicio final incremento
    //rango step
    /*
    for (i in 1 .. 4 step ){
        println(i)
    }
     */
    for (i in 10 downTo 0 step 5){
        println(i)
    }
    //(int)(Math.random()*21)
    (1.. 200).random()
}






class miclase {


}

