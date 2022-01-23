package space.kuz.appmaterialdesign.ui.viewmodel

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import space.kuz.appmaterialdesign.BuildConfig
import space.kuz.appmaterialdesign.data.retrofit.NasaApiRetrofit
import space.kuz.appmaterialdesign.domain.entity.DailyImage
import space.kuz.appmaterialdesign.domain.entity.NASAImageResponse as NASAImageResponse

class DailyImageViewModel(
    private val liveDataForViewToObserve: MutableLiveData<DailyImage> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit(),
) : ViewModel() {

    fun getImageData(): LiveData<DailyImage> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    fun openWiki(word:String): Intent{
        val intent = Intent(Intent.ACTION_VIEW)
        val url = "https://en.wikipedia.org/wiki/${word}"
        val uri = Uri.parse(url)
        intent.data = uri
        return  intent
    }

     fun sendServerRequest() {
        liveDataForViewToObserve.value = DailyImage.Loading(null)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            DailyImage.Error(Throwable("Нужен API ключ"))
        } else {
            executeImageRequest(apiKey)
        }
    }

    private fun executeImageRequest(apiKey: String) {
        retrofitImpl.getNasaService()
            .getImage(apiKey)
            .enqueue(object : retrofit2.Callback<NASAImageResponse> {
                override fun onResponse(
                    call: Call<NASAImageResponse>,
                    response: Response<NASAImageResponse>,
                ) {
                    handleImageResponse(response)
                }

                override fun onFailure(call: Call<NASAImageResponse>, t: Throwable) {
                    liveDataForViewToObserve.value = DailyImage.Error(t)
                }

            })
    }

    private fun handleImageResponse(response: Response<NASAImageResponse>) {
        if (response.isSuccessful && response.body() != null) {
            liveDataForViewToObserve.value = DailyImage.Success(response.body()!!)
            return
        }

        val message = response.message()
        if (message.isNullOrEmpty()) {
            liveDataForViewToObserve.value = DailyImage.Error(Throwable("Unidentified error"))
        } else {
            liveDataForViewToObserve.value = DailyImage.Error(Throwable(message))
        }
    }

}
