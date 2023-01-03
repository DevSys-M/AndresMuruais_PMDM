package com.example.t4_holderinterfaz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.t4_holderinterfaz.R
import com.example.t4_holderinterfaz.model.Lenguaje
import com.google.android.material.snackbar.Snackbar

// la clase AdaptadorLenguajes pide en constructor dos elementos: el contexto donde se ejecutará (será la activity donde se va a declarar) y la lista de datos que debe mostrar
// clase se un adaptador válido para un recyclerView es necesario que implemente la interfaz de RecyclerView.Adapter (la cual tiene una personalización de datos con una clase anidada que actua como holder)
class AdaptadorLenguajes(var contexto: Context, var listaDatos: List<*>):
    RecyclerView.Adapter<AdaptadorLenguajes.MyHolder>() {
    /*
     Al haber implementado una interfaz, la clase está obligada a utilizar todos los métodos que no tienen definición que son los siguientes:
            getItemCount(): encargado de retornar cuantos elementos se deben pintar dentro de la lista. Por defecto se retornará el tamaño de la lista pasada en constructor, ya que son los datos que se quieren representar
            onCreateViewHolder(): es el método encargado de crear un objeto de la clase anidada, pasando por constructor la vista o xml que tendrá la fila. Este retorno será el que utilice el método siguiente para poder pintar cada uno de los objetos correspondientes.
            onBindViewHolder(): encargado de representar en cada una de las filas obtenidas del método anterior (holder) el objeto que corresponda.
     */
        //TODO EVENTOS 2
        //el dato pueda llevarse a la actividad donde está declarado el recycler.
        //1.En el fichero adaptador (origen de los datos) es necesario crear una interfaz con un método que reciba por parámetros el dato que se quiere enviar a la activity
        private lateinit var listener: OnLenguajeListener

        interface OnLenguajeListener{
            fun onLenguajeClick(lenguaje: Lenguaje)
        }


    // recibe por parámetros un ViewGroup (que es el conjunto de vistas donde se representarán los elementos) y un viewType (por si hay diferentes tipos de vistas). Deberá retornar un objeto de la clase anidada, para lo cual necesita la vista que se creo anteriormente y para lo cual se utiliza un objeto de tipo LayoutInflater para podre obtenerla
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view: View = LayoutInflater.from(contexto).inflate(R.layout.fila_recycler,parent,false)
        return MyHolder(view)
    }
    //Se encarga de juntar la parte que obtiene del método anterior con el objeto que le corresponde en la posición determinada. Es por ello que recibe un objeto de tipo Holder (del método anterior) y un Int (la posición).
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var lenguaje: Lenguaje = listaDatos.get(position) as Lenguaje
        holder.imagen.setImageResource(lenguaje.imagen)
        holder.nombre.setText(lenguaje.nombre)
        //TODO EVENTOS 2
        //también es posible declararlo aquí
        //holder.imagen.setOnClickListener { listener.onLenguajeClick(listaDatos.get(position) as Lenguaje) }

        //TODO EVENTOS 1
        /*
            Los eventos se gestionan directamente desde la clase adaptador, en concreto desde el método onBindViewHolder ya que es ahí donde se personaliza la vista y se hace realizar. Esto otorga mucha flexibilidad ya que no tiene porque ser la pulsación en toda la fila, sino en una parte concreta de la fila, por ejemplo en un botón de ver detalle.
            Por lo tanto si se quiere configurar que al pulsar en la imagen del lenguaje salte un snackbar se tendría que poner un escuchador al elemento ubicado en la clase AdaptadorLenguaje.
        */
        holder.imagen.setOnClickListener {
            Snackbar.make(
                holder.imagen,
                "El desarrollador es ${(listaDatos.get(position) as Lenguaje).version.toString()}",
                Snackbar.LENGTH_LONG
            ).show()
            //En este método si se cuenta con un parámetro que indica la posición del item pulsado, por lo que se accede a la posición de lista y se saca el dato que se quiera.
            // De esta forma se podría sacar el item pulsado de la lista, pero el dato no sale de la clase adaptador.
        }
    }
    // ya que no recibe nada por parámetros y se encarga de retornar el tamaño de la lista de datos (la cual ha sido pasada por constructor)
    override fun getItemCount(): Int {
        return listaDatos.size
    }
    /*
    clase anidada que actúa como holder (o elemento en bruto) es la que permite manejar gran cantidad de datos, ya que permite "cachear" la información gráfica. Por ello esta clase extiende de ReciclerView.
    Holder, en constructor necesita una vista (que será el item fila creado anteriormente) y en el inicio postergado (init) inicia cada uno de los elementos de la fila
     */
    inner class MyHolder ( var view: View): RecyclerView.ViewHolder(view) {

        lateinit var nombre: TextView
        lateinit var imagen: ImageView
        /*
        init {
            nombre = view.findViewById(R.id.nombre_item)
            imagen = view.findViewById(R.id.imagen_item)
        }
         */
        //TODO EVENTO 2
        //Esta interfaz representará el evento que queremos lanzar con la pulsación de la imagen. Además de esto tendremos que inicializar la variable para que no nos pueda dar un nullpointer, por lo que se hará en el método init
        init {
            nombre = view.findViewById(R.id.nombre_item)
            imagen = view.findViewById(R.id.imagen_item)
            listener = contexto as OnLenguajeListener;
            //Esta igualdad es posible porque en los siguientes pasos se va a implementar la interfaz en la activity, por lo que serían del mismo tipo gracias al polimorfismo
            //Por último antes de acabar este paso se tendría que configurar el listener en la imagen, para que cuando sea pulsada se ejecute el método de la interfaz, pasando por parámetros los datos que se quiere comunicar. Esto se puede hacer tanto en el método onBindViewHolder como en el método init
            imagen.setOnClickListener { listener.onLenguajeClick(listaDatos.get(adapterPosition) as Lenguaje) }
        }

    }

}