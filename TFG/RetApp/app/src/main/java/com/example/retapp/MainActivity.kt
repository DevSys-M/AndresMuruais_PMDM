package com.example.retapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.retapp.profile.ProfileActivity

// TODO
// el retaicon.xml hace crashear la pagina, se ha sustituido por mi proyecto.png por ahora, si hace falta el vector de imagen, habrá que crear otro que no de problemas
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id: Int = item.itemId

        if (id == R.id.mnuProfile) {

            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))

        }


        return super.onOptionsItemSelected(item)
    }
}