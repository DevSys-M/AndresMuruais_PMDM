package model

class Asalariado(nombre: String, apellido: String, edad: Int,var correo:String) : Persona(nombre, apellido, edad) {

        private var NSS: Int=0;
        private var salario: Int? = null;
        private var retenciones: Int=1;
        private var pagas: Int=12;


    constructor(nombre: String,apellido: String,edad: Int,correo: String,nss: Int,salrio: Int): this(nombre,apellido,edad,correo){
        this.NSS = nss;
        this.salario = salario;
    }
    //crear metodo que te permite ver los datos del salario del usuario

    // bruto anual, neto anual
    // neto mensual
    // necesito que el usuario me diga el %retencion que quiere aplicar

    fun calculoSalarios(retneciones: Int){
        var retencionesCalcualdas: Double = this.salario!! * (retenciones/100.toDouble())
        println("El salario neto anual es ${this.salario!! - retencionesCalcualdas}")
        println("El salario neto mensual es ${(this.salario!! - retencionesCalcualdas)/this.pagas}")


        /*
        multiplicacion
        println("Ejecucion ${salario?.plus(20)}")
        multiplicacion
        println("Ejecucion ${salario!! * 20}")

         */
    }


    override fun mostrarDatos() {
        super.mostrarDatos()
        println("NSS: $NSS")
        println("Correo: $correo")
        println("Salario: $salario")
    }

    fun setNSS(nss: Int){
       this.NSS = nss
    }

    fun getNSS(): Int{
        return this.NSS
    }

    fun getSalario(): Int{
        return this.salario!!
    }
    fun setSalario(salario: Int){
       this.salario=salario
    }
}