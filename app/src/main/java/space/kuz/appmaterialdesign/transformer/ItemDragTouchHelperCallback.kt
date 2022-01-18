package space.kuz.appmaterialdesign.transformer

import androidx.recyclerview.widget.*

class ItemDragTouchHelperCallback(
    private  val onItemMove:(from:Int, to:Int)-> Unit,
    private  val onItemSwiped:(position:Int)-> Unit
) : ItemTouchHelper.Callback(){

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
       val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.START or ItemTouchHelper.END
        return  makeMovementFlags(
            dragFlag, swipeFlag
        )
    }

    override fun onMove(
        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
    ): Boolean {
      val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition
        onItemMove(fromPosition, toPosition)
        return  true
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
      onItemSwiped(viewHolder.adapterPosition)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return  true
    }
}