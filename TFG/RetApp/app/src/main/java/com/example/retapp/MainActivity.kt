package com.example.retapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.retapp.findfriends.FindFriendsFragment
import com.example.retapp.profile.ProfileActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO
// el retaicon.xml hace crashear la pagina, se ha sustituido por mi proyecto.png por ahora, si hace falta el vector de imagen, habrá que crear otro que no de problemas
class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabMain)
        viewPager = findViewById(R.id.vpMain)

        setViewPager()
    }


    // RecyclerView adapter implementado como clase interna
    inner class Adapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

        override fun getItemCount(): Int {
            return tabLayout.tabCount
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> ChatFragment()
                1 -> RequestsFragment()
                2 -> FindFriendsFragment()
                else -> throw IllegalArgumentException("Fragmento inexistente: $position")
            }
        }
    }


    // Establecer las tabs en el ViewPager2
    private fun setViewPager() {

        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_chat))
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_requests))
        tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_findfriends))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL // rellenar la pantalla

        var adapter: Adapter = Adapter(supportFragmentManager, lifecycle )
        viewPager.adapter = adapter
        // Comportamiento al pulsar cada una de las tabs
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // No ocurre nada
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Tampoco ocurre nada
            }
        })





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

    val coroutineScope = CoroutineScope(Dispatchers.Main)
    private var doubleBackPressed: Boolean = false
    override fun onBackPressed() {
        // super.onBackPressed() funcionalidad para cerrar aplicación, no interesa

        if (tabLayout.selectedTabPosition > 0) {

            tabLayout.selectTab(tabLayout.getTabAt(0))
        }

        else {

            if (doubleBackPressed) {

                finishAffinity()

            }
            else {
                doubleBackPressed = true
                Toast.makeText(this@MainActivity, getString(R.string.press_back_to_exit), Toast.LENGTH_SHORT).show()
                // delay
                coroutineScope.launch {
                    delay(2000)
                    doubleBackPressed = false // si pasan dos segundos y no se pulsa, vuelve a false
                }


            }

        }
    }






}