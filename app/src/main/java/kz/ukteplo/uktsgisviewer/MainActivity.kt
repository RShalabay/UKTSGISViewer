package kz.ukteplo.uktsgisviewer

import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.webkit.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    private val mRetrofitManager: RetrofitManager = RetrofitManager.getInstance()
    private lateinit var mainMenu: Menu

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.app_name)

        val i: Intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(i)
        //drawMap()

    }

    private fun drawMap() {
        val context = this
        job = Job()
        launch {
            val result = withContext(Dispatchers.IO) {
                var request: Request = Request()
                request.deviceid = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
                Log.d("deviceId", request.deviceid)
                if (request.deviceid.isEmpty()) request.deviceid = "35" + Build.BOARD.length % 10 +
                        Build.BRAND.length % 10 +
                        Build.CPU_ABI.length % 10 +
                        Build.DEVICE.length % 10 +
                        Build.DISPLAY.length % 10 +
                        Build.HOST.length % 10 +
                        Build.ID.length % 10 +
                        Build.MANUFACTURER.length % 10 +
                        Build.MODEL.length % 10 +
                        Build.PRODUCT.length % 10 +
                        Build.TAGS.length % 10 +
                        Build.TYPE.length % 10 +
                        Build.USER.length % 10
                mRetrofitManager.uktsApi.getGisMapUrl(request).execute()
            }
            if (result.isSuccessful) {
                val response = result.body()
                if (response != null) {
                    if (response.success) {
                        val myWebView: WebView = findViewById(R.id.wv)
                        myWebView.settings.javaScriptEnabled = true
                        myWebView.settings.loadWithOverviewMode = true
                        myWebView.settings.useWideViewPort = true
                        myWebView.settings.domStorageEnabled = true
                        myWebView.webViewClient = MyWebViewClient()
                        myWebView.webChromeClient = WebChromeClient()
                        myWebView.addJavascriptInterface(WebAppInterface(applicationContext), "Android")
                        myWebView.loadUrl("https://ukteplo.kz/gis/" + response.gisMapUrl + "/")
                        Toast.makeText(applicationContext, "Загружаем карту. Пожалуйста, подождите...", Toast.LENGTH_SHORT).show()

                    } else {
                        var args = Bundle()
                        args.putString("msg", response.errorDescription)
                        args.putString("code", Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID).takeLast(4))
                        var alertDialog = AlertDialogFragment()
                        alertDialog.arguments = args
                        alertDialog.show(supportFragmentManager, alertDialog.tag)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            mainMenu = menu
            menuInflater.inflate(R.menu.main, mainMenu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawMap()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}

class WebAppInterface(private val mContext: Context) {
    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
    }
}

private class MyWebViewClient : WebViewClient() {

}