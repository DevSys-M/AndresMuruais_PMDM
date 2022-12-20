package model

//final por defecto
//open

// si en una clase le marco parametro si tiene constructor primario sino solo secundario
class Coche (var marca:String,var modelo:String) {

    //atributos
    var bastidor:String?=null;
    private var cv: Int=0
        get() = field
        set(cv){
            println("valor modificado")
            field = cv;
        }

    var cc: Int=0;
    lateinit var propietario: Propietario
    //constructores
        //PRIMARIOS
    // public Coche(){}
        //SECUNDARIO

    /*constructor(marca:String,modelo:String){
            this.marca=marca;
            this.modelo=modelo;
        }

     */
    constructor(marca:String,modelo:String, bastidor:String): this(marca,modelo){
        this.bastidor=bastidor;
    }
    /*
    //bloque ejecutando tras cualquier constructor pero es general para todos los objetos
    init {
        propietario = Propietario("Borja","Martin","123A");
    }
     */
    //funciones

    fun asignarPropietario(propietario: Propietario){
        this.propietario=propietario;
    }

    // funcion de callback
    fun calcularParMotor (calculoPar:(Int)->Int, aceleracion: Int){
        calculoPar(aceleracion);
    }
    var mostrarDatos: ()->Unit ={
        println("Marca: $marca")
        println("Modelo: ${modelo}")
        println("CV: $cv")
        println("CC: $cc")
    }
    //getter setter

    fun setCV(cv: Int){
        this.cv=cv;
    }
    fun getCV():Int{
        return this.cv
    }
    //toString
    override fun toString(): String {
        return super.toString()
    }

}