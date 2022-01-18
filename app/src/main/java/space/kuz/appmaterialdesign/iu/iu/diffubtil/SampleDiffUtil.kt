package space.kuz.appmaterialdesign.iu.iu.diffubtil

import androidx.recyclerview.widget.DiffUtil
import space.kuz.appmaterialdesign.domain.model.*
import java.lang.IllegalArgumentException

class SampleDiffUtil(
    private val oldList:List<BookUiModel>,
    private val newList:List<BookUiModel>
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
            is BookUiModel -> newItem is BookUiModel && oldItem.id == newItem.id
            else -> throw  IllegalArgumentException("unknown item type")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when (oldItem){
            is BookUiModel -> newItem is BookUiModel && oldItem == newItem
            else -> throw  IllegalArgumentException("unknown item type")
        }
    }
}