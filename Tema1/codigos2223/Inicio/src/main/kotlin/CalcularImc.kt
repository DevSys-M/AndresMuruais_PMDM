fun main() {
    var peso: Int = 10000
    var altura: Double = 1.70

    println("IMC  ${calcularImc(peso, altura)}")

}




fun calcularImc(peso: Int ,altura: Double): Int {
        var imc = (peso / (altura * altura)).toInt()

        return imc
    }

