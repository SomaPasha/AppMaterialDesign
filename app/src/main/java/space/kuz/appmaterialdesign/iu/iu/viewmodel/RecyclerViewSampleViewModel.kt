package space.kuz.appmaterialdesign.iu.iu.viewmodel

import android.bluetooth.le.AdvertiseCallback
import androidx.lifecycle.*
import space.kuz.appmaterialdesign.domain.model.*
import java.io.FileDescriptor
import java.lang.IllegalStateException
import java.text.ParsePosition
import java.util.*
import kotlin.collections.ArrayList

private const val bookPictureUrl= "https://cdn-icons-png.flaticon.com/512/43/43212.png"
class RecyclerViewSampleViewModel:ViewModel() {
    private  val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())

    private  val messageLiveData  = MutableLiveData<String>()

    fun getItems():LiveData<List<SampleListItem>>{
        return  itemsLiveData
    }

    fun getMessage():LiveData<String>{
        return  messageLiveData
    }

    fun addItem(title:String, description:String){
        val planet1 = PlanetUiModel(
            id = UUID.randomUUID().toString(),
            description = description,
            title = title,
            pictureUrl = bookPictureUrl
        )
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.add(planet1)
        itemsLiveData.value = newMutableList
    }


    fun onPlanetClick(uiModel:PlanetUiModel){
        messageLiveData.value = uiModel.title
        val oldList = requireCurrentList()
        val newList = oldList - uiModel
        itemsLiveData.value = newList
    }

    fun onItemMoved(from:Int,to:Int){
        val newMutableList = requireCurrentList().toMutableList()
        Collections.swap(newMutableList,from,to)
        itemsLiveData.value = newMutableList
    }

    fun onItemRemoved(position: Int){
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.removeAt(position)
        itemsLiveData.value = newMutableList
    }

    private fun requireCurrentList():List<SampleListItem>{
        return itemsLiveData.value?:throw IllegalStateException("items list is null")
    }


}