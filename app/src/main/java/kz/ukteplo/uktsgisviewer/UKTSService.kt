package kz.ukteplo.uktsgisviewer

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UKTSService {
    @POST("?getGisMapUrl")
    fun getGisMapUrl(@Body request: Request): Call<Response>
}