package space.kuz.appmaterialdesign.iu.iu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import space.kuz.appmaterialdesign.domain.entity.DailyImage

class DailyImageViewModel():ViewModel() {
    private val liveDataForViewToObserve:MutableLiveData<DailyImage> = MutableLiveData()
}