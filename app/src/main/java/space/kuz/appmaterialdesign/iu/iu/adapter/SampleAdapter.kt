package space.kuz.appmaterialdesign.iu.iu.adapter

import android.text.Editable
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.model.*


class SampleAdapter(
    private val onBookClickListener:((item:BookUiModel)->Unit),
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items = emptyList<BookUiModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return BookViewHolder(
                    view = inflater.inflate(R.layout.item_planet_view, parent,false) as View,
                    onBookClickListener = onBookClickListener
                )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           holder as BookViewHolder
           val planetUiModel = items[position] as BookUiModel
           holder.bind(planetUiModel)
    }

    override fun getItemCount(): Int {
    return items.size
    }

}

class BookViewHolder (
    view:View,
    private  val onBookClickListener: (item:BookUiModel)-> Unit
    ):RecyclerView.ViewHolder(view){
    private  val bookImageView: ImageView = view.findViewById(R.id.image_view_book)
        private  val bookEditText: EditText = view.findViewById(R.id.edit_view_book) as EditText
    private  val bookNameEditText: EditText = view.findViewById(R.id.edit_view_book_name) as EditText

    fun bind(bookUiModel:BookUiModel){
        Glide.with(bookImageView.context).load(bookUiModel.pictureUrl).into(bookImageView)
        bookEditText.text = Editable.Factory.getInstance().newEditable(bookUiModel.description)
        bookNameEditText.text=  Editable.Factory.getInstance().newEditable(bookUiModel.title)
        bookEditText.isEnabled= false
        bookNameEditText.isEnabled = false
        itemView.setOnClickListener{
            onBookClickListener(bookUiModel)
            bookEditText.isEnabled =  !bookEditText.isEnabled
            bookNameEditText.isEnabled = !bookNameEditText.isEnabled
        }
    }

    }