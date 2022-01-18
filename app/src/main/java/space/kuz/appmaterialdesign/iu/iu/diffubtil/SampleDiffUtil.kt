package space.kuz.appmaterialdesign.iu.iu.diffubtil

import androidx.recyclerview.widget.DiffUtil
import space.kuz.appmaterialdesign.domain.model.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class SampleDiffUtil(
    private val oldList:List<PlanetUiModel>,
    private val newList:List<PlanetUiModel>
):DiffUtil.Callback() {

    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
       return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when (oldItem){
            is PlanetUiModel -> newItem is PlanetUiModel && oldItem.id == newItem.id
            else -> throw  IllegalArgumentException("unknown item type")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when (oldItem){
            is PlanetUiModel -> newItem is PlanetUiModel && oldItem == newItem
            else -> throw  IllegalArgumentException("unknown item type")
        }
    }
}