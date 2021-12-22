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




        /*try {

        } catch (e: Exception) {
            Log.e("MAP", e.localizedMessage)
            e.printStackTrace()
        }*/
        //}
// Add a marker in Sydney and move the camera
        val sydney = LatLng(50.369566, 83.311246)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        job = Job()

        try{
            /*launch(Dispatchers.IO) {
                layer = KmlLayer(mMap, R.raw.streets, applicationContext)
                launch(Dispatchers.Main) {
                    layer.addLayerToMap()
                    Toast.makeText(applicationContext, "Слой Улицы загружен", Toast.LENGTH_SHORT)
                }
                Log.d("MAP", "Слой Улицы загружен")
            }
            launch(Dispatchers.IO) {
                layer1 = KmlLayer(mMap, R.raw.house, applicationContext)
                launch(Dispatchers.Main) {
                    layer1.addLayerToMap()
                    Toast.makeText(applicationContext, "Слой Жилые загружен", Toast.LENGTH_SHORT)
                }
                Log.d("MAP", "Слой Жилые загружен")
            }

            launch(Dispatchers.IO) {
                layer2 = KmlLayer(mMap, R.raw.irtysh, applicationContext)
                launch(Dispatchers.Main) {
                    layer2.addLayerToMap()
                    Toast.makeText(applicationContext, "Слой Иртыш загружен", Toast.LENGTH_SHORT)
                }
                Log.d("MAP", "Слой Иртыш загружен")
            }

            launch(Dispatchers.IO) {
                layer3 = KmlLayer(mMap, R.raw.labels, applicationContext)
                launch(Dispatchers.Main) {
                    layer3.addLayerToMap()
                    Toast.makeText(applicationContext, "Слой Метки загружен", Toast.LENGTH_SHORT)
                }
                Log.d("MAP", "Слой Метки загружен")
            }*/

            launch(Dispatchers.IO) {
                layer4 = KmlLayer(mMap, R.raw.net, applicationContext)
                launch(Dispatchers.Main) {
                    layer4.addLayerToMap()
                    Toast.makeText(applicationContext, "Слой Сети загружен", Toast.LENGTH_SHORT)
                }
                Log.d("MAP", "Слой Сети загружен")
            }
        }catch (e: Exception) {
            Log.e("MAP", e.localizedMessage)
        }



        //job = Job()
        //launch {
        /*    try {
                layer = KmlLayer(mMap, R.raw.streets, applicationContext)
                layer.addLayerToMap()
                layer1 = KmlLayer(mMap, R.raw.house, applicationContext)
                layer1.addLayerToMap()
                layer2 = KmlLayer(mMap, R.raw.irtysh, applicationContext)
                layer2.addLayerToMap()
                layer3 = KmlLayer(mMap, R.raw.labels, applicationContext)
                layer3.addLayerToMap()
                layer4 = KmlLayer(mMap, R.raw.net, applicationContext)
                layer4.addLayerToMap()
            } catch (e: Exception) {
                Log.e("MAP", e.localizedMessage)
                e.printStackTrace()
            }*/
        //}
    }
}