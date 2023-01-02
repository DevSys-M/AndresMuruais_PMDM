package com.example.t4_listasholder


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t4_listasholder.adapter.AdaptadorRecycler
import com.example.t4_listasholder.databinding.ActivityMainBinding
import com.example.t4_listasholder.model.Usuarios
import com.google.android.material.snackbar.Snackbar

//4. En le destino de los datos implementar la interfaz
class MainActivity : AppCompatActivity(), AdaptadorRecycler.OnRecyclerUsuarioListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var listaUsuarios: ArrayList<Usuarios>
    private lateinit var adaptadorRecycler:AdaptadorRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instancias()

    }



    private fun instancias() {
        listaUsuarios= ArrayList()
        listaUsuarios.add(Usuarios("Borja","Martin","correo@retamar.es",25))
        listaUsuarios.add(Usuarios("Juan","Gomez","correo@retamar.es",30))
        listaUsuarios.add(Usuarios("Gonzalo","Fernandez","correo@retamar.es",44))
        listaUsuarios.add(Usuarios("Pedro","Lopez","correo@retamar.es",26))
        listaUsuarios.add(Usuarios("Celia","Martin","correo@retamar.es",45))
        adaptadorRecycler = AdaptadorRecycler(this,listaUsuarios)
        binding.listaRecycler.adapter = adaptadorRecycler
        binding.listaRecycler.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.HORIZONTAL,false)
        // binding.listaRecycler.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
        //binding.listaRecycler.layoutManager =  GridLayoutManager(applicationContext, 2,GridLayoutManager.VERTICAL, false)


        binding.botonAdd.setOnClickListener {
            listaUsuarios.add(Usuarios(binding.textoNombre.text.toString(),binding.textoApellido.text.toString(),binding.textoCorreo.text.toString(),0))
            //adaptadorRecycler.notifyDataSetChanged()
            adaptadorRecycler.notifyItemInserted(listaUsuarios.size-1);
        }

        //Meter por nombre apellido correo
        // Crear boton crea un elemento dentro de la lista
    }

    override fun comunicarUsuarioSelected(usuario: Usuarios) {
        // Utilizar los datos
        Snackbar.make(binding.listaRecycler,"Comunicado ${usuario.nombre}",Snackbar.LENGTH_SHORT).show()
    }

    override fun comunicarUsuarioSelected(usuario: Usuarios, posicion: Int) {
        // Utilizar los datos
        Snackbar.make(binding.listaRecycler,"Comunicado ${usuario.nombre} en posicion {$posicion}",Snackbar.LENGTH_SHORT).show()
    }
}