package kz.ukteplo.uktsgisviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.collections.GroundOverlayManager
import com.google.maps.android.collections.MarkerManager
import com.google.maps.android.collections.PolygonManager
import com.google.maps.android.collections.PolylineManager
import com.google.maps.android.data.kml.KmlLayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.ukteplo.uktsgisviewer.databinding.ActivityMapsBinding
import kotlin.coroutines.CoroutineContext

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private lateinit var layer: KmlLayer
    private lateinit var layer1: KmlLayer
    private lateinit var layer2: KmlLayer
    private lateinit var layer3: KmlLayer
    private lateinit var layer4: KmlLayer

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //mMap.uiSettings.isMyLocationButtonEnabled = true

        /*val sydney = LatLng(50.369566, 83.311246)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/
        drawKml(mMap)
        //job = Job()


    }

    fun drawKml(map: GoogleMap) {
        val markerManager = MarkerManager(map)
        val groundOverlayManager = GroundOverlayManager(map!!)
        val polygonManager = PolygonManager(map)
        val polylineManager = PolylineManager(map)

        val clusterManager = ClusterManager<ClusterItem>(applicationContext, map, markerManager)

        val kml_streets = KmlLayer(map,
            R.raw.streets,
            applicationContext,
            markerManager,
            polygonManager,
            polylineManager,
            groundOverlayManager,
            null)
            .addLayerToMap()
        val kml_irtysh = KmlLayer(map,
            R.raw.irtysh,
            applicationContext,
            markerManager,
            polygonManager,
            polylineManager,
            groundOverlayManager,
            null)
            .addLayerToMap()
    }
}