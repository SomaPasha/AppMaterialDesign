package space.kuz.appmaterialdesign.data.retrofit

import retrofit2.Call
import retrofit2.http.*
import space.kuz.appmaterialdesign.domain.entity.NASAImageResponse

interface NasaApiService {
    @GET("planetary/apod")
    fun getImage(@Query("api_key") apiKey: String): Call<NASAImageResponse>
}