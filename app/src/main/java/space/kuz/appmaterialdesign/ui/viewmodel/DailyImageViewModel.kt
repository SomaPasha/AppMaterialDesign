package space.kuz.appmaterialdesign.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.*
import retrofit2.*
import space.kuz.appmaterialdesign.BuildConfig
import space.kuz.appmaterialdesign.data.retrofit.NasaApiRetrofit
import space.kuz.appmaterialdesign.domain.entity.*

class DailyImageViewModel(
    private val liveDataForViewToObserve: MutableLiveData<DailyImage> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit(),
) : ViewModel() {

    fun getImageData(): LiveData<DailyImage> {
        sendServerRequest()
        return liveDataForViewToObserve
    }

    fun openUri(word: String): Uri {
        val url = "https://en.wikipedia.org/wiki/${word}"
        val uri = Uri.parse(url)
        return uri
    }


    fun sendServerRequest2() {
        liveDataForViewToObserve.value = DailyImage.Loading(null)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            DailyImage.Error(Throwable("Нужен API ключ"))
        } else {
            executeImageRequest(apiKey)
        }
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
        retrofitImpl.getNasaService().getImage(apiKey)
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
