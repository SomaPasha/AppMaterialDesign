package space.kuz.appmaterialdesign.iu.iu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import space.kuz.appmaterialdesign.iu.iu.adapter.UniverseScreen
import space.kuz.appmaterialdesign.iu.iu.universescreen.DailyUniverseScreen
import space.kuz.appmaterialdesign.iu.iu.universescreen.EarthUniverseScreen

class UniverseViewModel(
    private val liveDataForViewToObserve: MutableLiveData<List <UniverseScreen>> = MutableLiveData()
) : ViewModel() {
    fun getFragmentUniverse(): LiveData<List <UniverseScreen>> {

        liveDataForViewToObserve.value =
            listOf(
                DailyUniverseScreen()
                ,EarthUniverseScreen()
            )
        return liveDataForViewToObserve
    }
}
