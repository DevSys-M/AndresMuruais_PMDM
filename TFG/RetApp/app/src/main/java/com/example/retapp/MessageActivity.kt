package com.example.retapp

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.retapp.common.Util
import androidx.annotation.NonNull
import org.w3c.dom.Text

class MessageActivity : AppCompatActivity() {


    private lateinit var tvMessage: TextView
    private lateinit var pbMessage: ProgressBar
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        tvMessage = findViewById(R.id.tvMessage)
        pbMessage = findViewById(R.id.pbMessage)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                // cada metodo se ejecutara si hay conexion o no
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    finish()
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    tvMessage.setText(R.string.no_internet)
                }
            }

            var connectivityManager: ConnectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

            connectivityManager.registerNetworkCallback( NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build(), networkCallback) // cada vez que haya un cambio en la conexion se volvera a ejecutar uno de los metodos onAvailable / onLost

        }

    }


    fun btnRetryClick(v: View) {
        pbMessage.visibility = View.VISIBLE

        val util = Util()
        if(util.connectionAvailable(this)) {
            finish()

        }
        else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        pbMessage.visibility = View.GONE
                    }, 1000)

        }

    }


    fun btnCloseClick(view: View) {

        // cierra todas las activities de la aplicación
        finishAffinity()

    }


}