package kz.ukteplo.uktsgisviewer

import android.os.Build
import android.provider.Settings
import android.util.Log

class Request {
    val apikey: String
    var deviceid: String
    val lang: String

    init {
        apikey = "b333a61c-004f-409b-811a-52be285d9efd"
        deviceid = ""
        lang = "ru"
        Log.d("deviceId", deviceid)
    }

}