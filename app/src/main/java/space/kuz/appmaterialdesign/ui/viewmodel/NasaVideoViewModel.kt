package space.kuz.appmaterialdesign.iu.iu.viewmodel

import androidx.lifecycle.*
import retrofit2.*
import space.kuz.appmaterialdesign.BuildConfig
import space.kuz.appmaterialdesign.data.retrofit.NasaApiRetrofit
import space.kuz.appmaterialdesign.domain.entity.*

class NasaVideoViewModel(

    private val liveDataForViewToObserve: MutableLiveData<NasaVideoSealed> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit()
) : ViewModel() {

    fun getVideoData(): LiveData<NasaVideoSealed> {
        sendServerRequestEarth()
        return liveDataForViewToObserve
    }

    private fun sendServerRequestEarth() {
        liveDataForViewToObserve.value = NasaVideoSealed.Loading(null)
        val apiKey = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {
            EarthImage.Error(Throwable("Нужен API ключ"))
        } else {
            executeImageRequestEarth(apiKey)
        }
    }

    private fun executeImageRequestEarth(apiKey: String) {
        retrofitImpl.getNasaService().getVideo(apiKey).enqueue(object : retrofit2.Callback<NASAVideo> {
            override fun onResponse(
                call: Call<NASAVideo>, response: Response<NASAVideo>
            ) {
                handleImageResponseEarth(response)
            }

            override fun onFailure(call: Call<NASAVideo>, t: Throwable) {
                liveDataForViewToObserve.value = NasaVideoSealed.Error(t)
            }

        })
    }

    private fun handleImageResponseEarth(response: Response<NASAVideo>) {
        if (response.isSuccessful && response.body() != null) {
            liveDataForViewToObserve.value = NasaVideoSealed.Success(response.body()!!)
            return
        }

        val message = response.message()
        if (message.isNullOrEmpty()) {
            liveDataForViewToObserve.value = NasaVideoSealed.Error(Throwable("Unidentified error"))
        } else {
            liveDataForViewToObserve.value = NasaVideoSealed.Error(Throwable(message))
        }
    }

}