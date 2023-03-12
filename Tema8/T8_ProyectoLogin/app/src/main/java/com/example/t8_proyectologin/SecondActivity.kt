package com.example.t8_proyectologin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t8_proyectologin.adapter.AdapterRecycler
import com.example.t8_proyectologin.databinding.ActivitySecondBinding
import com.example.t8_proyectologin.model.Producto
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class SecondActivity : AppCompatActivity() {

    private lateinit var binding:  ActivitySecondBinding

    private lateinit var dataBase: FirebaseDatabase

    private lateinit var adapterRecycler: AdapterRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase =
            FirebaseDatabase.getInstance("https://pdpm-project-default-rtdb.europe-west1.firebasedatabase.app/")
        binding = ActivitySecondBinding.inflate(layoutInflater)

        adapterRecycler = AdapterRecycler(applicationContext)
        binding.recyclerProductos.adapter = adapterRecycler
        binding.recyclerProductos.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)

        val uid = intent.extras!!.getString("uid")
        val nombre = intent.extras!!.getString("nombre")
        val edad = intent.extras!!.getString("edad")

        val referencia = dataBase.getReference("usuarios").child(uid!!)

        referencia.child("datos").child("nombre").setValue(nombre)
        referencia.child("datos").child("edad").setValue(edad)


        setContentView(binding.root)

        binding.botonAgregar.setOnClickListener {
            // agregar datos a la base de datos

            val referenciaProductos= referencia.child("productos")
            //referenciaProductos.child("ordendor").child("nombre").setValue("ordendor")
            //referenciaProductos.child("ordendor").child("valor").setValue(123)
            referenciaProductos.child("coche").setValue(Producto("coche",123534))

            // agregar datos a la base de datos
            //val referencia = dataBase.getReference("usuarios").child("usuario2")
            //referencia.child("dni").setValue("12312312")
            //referencia.child("experiencia").setValue(false)
        }

        binding.botonConsultar.setOnClickListener {
            referencia.child("productos").addValueEventListener(object: ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //Log.v("base_datos",snapshot.key.toString())
                    for (item in snapshot.children){
                        //Log.v("base_datos",item.key.toString())

                        val producto = item.getValue(Producto::class.java)
                       // adapterRecycler.agregarProducto(producto!!)
                        adapterRecycler.verProductos(producto!!)
                        Log.v("base_datos","Producto ${producto!!.nombre} ${producto!!.valor}")

                        /*
                        for (item2 in item.children){
                            val nombre =
                        }
                         */
                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}