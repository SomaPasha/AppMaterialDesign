package space.kuz.appmaterialdesign.iu.iu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Response
import space.kuz.appmaterialdesign.BuildConfig
import space.kuz.appmaterialdesign.data.retrofit.NasaApiRetrofit
import space.kuz.appmaterialdesign.domain.entity.EarthImage
import space.kuz.appmaterialdesign.domain.entity.NASAImageEarth
import space.kuz.appmaterialdesign.domain.entity.NASAImageResponse

class EarthViewModel(
    private val liveDataForViewToObserve: MutableLiveData<EarthImage> = MutableLiveData(),
    private val retrofitImpl: NasaApiRetrofit = NasaApiRetrofit()
): ViewModel() {

    fun getImageDataEarth(): LiveData<EarthImage> {
        sendServerRequestEarth()
        return liveDataForViewToObserve
    }
    private fun sendServerRequestEarth(){
        liveDataForViewToObserve.value =EarthImage.Loading(null)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            EarthImage.Error(Throwable("Нужен API ключ"))
        } else{
            executeImageRequestEarth(apiKey)
        }
    }


    private fun executeImageRequestEarth(apiKey:String){
        retrofitImpl.getNasaService().getImageEarth(apiKey).enqueue(
            object : retrofit2.Callback<NASAImageEarth> {
                override fun onResponse(
                    call: Call<NASAImageEarth>,
                    response: Response<NASAImageEarth>
                ) {
                    handleImageResponseEarth(response)
                }

                override fun onFailure(call: Call<NASAImageEarth>, t: Throwable) {
                    liveDataForViewToObserve.value = EarthImage.Error(t)
                }

            }
        )
    }
    private fun handleImageResponseEarth(response: Response<NASAImageEarth>) {
        if (response.isSuccessful && response.body() != null) {
            liveDataForViewToObserve.value = EarthImage.SuccessEarth(response.body()!!)
            return
        }

        val message = response.message()
        if (message.isNullOrEmpty()) {
            liveDataForViewToObserve.value = EarthImage.Error(Throwable("Unidentified error"))
        } else {
            liveDataForViewToObserve.value = EarthImage.Error(Throwable(message))
        }
    }




}