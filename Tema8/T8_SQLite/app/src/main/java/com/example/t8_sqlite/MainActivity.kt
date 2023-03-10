package com.example.t8_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Database
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.t8_sqlite.database.DatabaseAlumnos
import com.example.t8_sqlite.databinding.ActivityMainBinding
import com.example.t8_sqlite.model.Alumno
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //json
        val url: String = "https://www.thesportsdb.com/api/v1/json/3/all_leagues.php"
        var peticion:JsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener {
            val ligas: JSONArray = it.getJSONArray("leagues")

            val ligaObjeto: JSONObject = ligas.getJSONObject(0)
            val nombreLiga: String = ligaObjeto.getString(("strLeague"))
            Log.v("json_datos",nombreLiga)


        }, null)

        Volley.newRequestQueue(applicationContext).add(peticion)
        //json


        GlobalScope.launch(Dispatchers.IO){
            val database= DatabaseAlumnos.getInstance(applicationContext)
            //database.alumnoDAO().insertarAlumno(Alumno("Andy","Murray", 25, "developandsys@gmail.com"))
            database.alumnoDAO().getAlumnos().forEach{
                val alumno = it;
                Log.v("base_datos",it.nombre)
                withContext(Dispatchers.Main){
                    binding.texto.text = alumno.nombre
                }
            }
        }
    }
}