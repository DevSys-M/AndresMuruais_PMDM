package com.example.geolocalizacionapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var btnAddMarker: Button
    private lateinit var btnRemoveMarkers: Button
    private lateinit var btnMoveToLocation: Button

    private val markers = mutableListOf<Marker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddMarker = findViewById(R.id.btnAddMarker)
        btnRemoveMarkers = findViewById(R.id.btnRemoveMarkers)
        btnMoveToLocation = findViewById(R.id.btnMoveToLocation)

        btnMoveToLocation.setOnClickListener { moveToCurrentLocation() }
        btnAddMarker.setOnClickListener { addMarker() }
        btnRemoveMarkers.setOnClickListener { removeMarkers() }

        // Configurar OpenStreetMap
        Configuration.getInstance().load(this, androidx.preference.PreferenceManager.getDefaultSharedPreferences(this))

        // Verificar los permisos de ubicación
        val permission = Manifest.permission.ACCESS_FINE_LOCATION
        val granted = PackageManager.PERMISSION_GRANTED
        val hasPermission = ContextCompat.checkSelfPermission(this, permission) == granted

        // Solicitar permiso de ubicación si no está otorgado
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 0)
        } else {
            setupMap()
        }
    }

    private fun addMarker() {
        val centerPoint = mapView.mapCenter as GeoPoint
        val marker = Marker(mapView)
        marker.position = centerPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Marcador"
        marker.snippet = "Latitud: ${centerPoint.latitude}, Longitud: ${centerPoint.longitude}"
        mapView.overlays.add(marker)
        markers.add(marker)
        mapView.invalidate()
    }

    private fun removeMarkers() {
        mapView.overlays.removeAll(markers)
        markers.clear()
        mapView.invalidate()
    }

    private fun setupMap() {
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(15.0)

        val startPoint = GeoPoint(40.42621664560739, -3.8060653171574113) // Ubicación de Colegio Retamar
        mapView.controller.setCenter(startPoint)

        val currentLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(this), mapView)
        currentLocationOverlay.enableMyLocation()
        mapView.overlays.add(currentLocationOverlay)

        val marker = Marker(mapView)
        marker.position = startPoint
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        marker.title = "Marcador"
        marker.snippet = "Ubicación de Colegio Retamar"
        mapView.overlays.add(marker)
    }

    private fun moveToCurrentLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            if (location != null) {
                val currentLocation = GeoPoint(location.latitude, location.longitude)
                mapView.controller.animateTo(currentLocation)
            } else {
                Toast.makeText(this, "No se pudo obtener la ubicación actual", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, object :
                LocationListener {
                override fun onLocationChanged(location: Location) {
                    val currentLocation = GeoPoint(location.latitude, location.longitude)
                    mapView.controller.setCenter(currentLocation)
                }

                override fun onProviderDisabled(provider: String) {}
                override fun onProviderEnabled(provider: String) {}
                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            }, null)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 0 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupMap()
            getCurrentLocation()
        } else {
            Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}
