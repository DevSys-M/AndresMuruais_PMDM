package com.example.t6_juegos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.t6_juegos.adapters.AdapterRecycler
import com.example.t6_juegos.databinding.ActivityMainBinding
import com.example.t6_juegos.dialogs.DialogoMultiple
import com.example.t6_juegos.model.Juegos

class MainActivity : AppCompatActivity(),DialogoMultiple.OnMultipleListener {
    lateinit var binding: ActivityMainBinding

    lateinit var adapterRecycler: AdapterRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportActionBar?.title = "Listado Juegos"  no funciona preguntar
        setSupportActionBar(binding.toolbar)

        //recycler
        adapterRecycler = AdapterRecycler(ArrayList(),this)
        binding.recyclerJuegos.adapter = adapterRecycler
        binding.recyclerJuegos.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        adapterRecycler.addJuego(Juegos("Elden Ring","xbox","rol",false,15,R.drawable.elden))
        adapterRecycler.addJuego(Juegos("Gran turismo","ps5","coches",false,15,R.drawable.gt7))
        adapterRecycler.addJuego(Juegos("Persona 5 Royal","switch","plataformas",false,15,R.drawable.personal))
        adapterRecycler.addJuego(Juegos("Red Dead Redemption","ps5, ","mundo abierto",false,15,R.drawable.red))
        adapterRecycler.addJuego(Juegos("Fifa 23","xbox","fútbol",false,15,R.drawable.fifa))
        adapterRecycler.addJuego(Juegos("Half life","pc","shooter",false,15,R.drawable.half))
        adapterRecycler.addJuego(Juegos("The Legend of Zelda","switch","plataformas",false,15,R.drawable.zelda))
        adapterRecycler.addJuego(Juegos("Super Mario","switch","plataformas",false,15,R.drawable.mario))
        adapterRecycler.addJuego(Juegos("Super Smash Bros","switch","peleas",false,15,R.drawable.smash))
        adapterRecycler.addJuego(Juegos("The Last of Us","ps5","aventuras",false,15,R.drawable.last))
        adapterRecycler.addJuego(Juegos("Resident Evil 7","ps5","terror",false,15,R.drawable.resident))
        adapterRecycler.addJuego(Juegos("GTA V","xbox","mundo abierto",false,15,R.drawable.gta))
        adapterRecycler.addJuego(Juegos("God of War","ps5","plataformas",false,15,R.drawable.god))
        adapterRecycler.addJuego(Juegos("Forza Horizon 4","xbox","coches",false,15,R.drawable.forza))
        adapterRecycler.addJuego(Juegos("BioShock","pc","futurista",false,15,R.drawable.bioshock))


        adapterRecycler.onJuegosOnClick={juegos: Juegos->
        var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("juegos",juegos)
            startActivity(intent)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_filtrar->{
                DialogoMultiple().show(supportFragmentManager,null)
            }
            R.id.menu_favoritos->{
                adapterRecycler.verFavoritos()
            }
            R.id.menu_borrar->{
               //crear metodo en recycler
            }
        }

        return true
    }

    override fun onMultipleSelected(lista: ArrayList<String>) {
            //adapterRecycler.filtarJuego(lista.toString())
    }
}