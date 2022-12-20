fun main() {
    ejercicioAleatorio()

    }
fun ejercicioAleatorio(){
    var edad: Int = 0
    println("Dime que edad tienes")
    edad = readLine()!!.toInt()
    if(edad>=18){
        var max= -1
        var min = 201
        var sumatorio = 0;
        for (i in 1 .. 10){
            var aleatorio = (1 .. 100).random()
            sumatorio += aleatorio
            if (aleatorio > max)
                max = aleatorio
            if (aleatorio < min)
                min = aleatorio
        }
        println("suma ${sumatorio}")
        println("minimo ${min}")
        println("maximo ${max}")
        println("promedio ${sumatorio/10}")
    }else{

        println("No eres mayor de edad")
    }

}