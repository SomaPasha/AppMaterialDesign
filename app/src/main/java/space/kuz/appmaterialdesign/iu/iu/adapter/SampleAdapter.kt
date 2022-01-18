package space.kuz.appmaterialdesign.iu.iu.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.model.*
import java.lang.IllegalStateException

//private const val viewTypePlanet = 0
//private const val viewTypeAdvertising  = 1

class SampleAdapter(
    private val onPlanetClickListener:((item:PlanetUiModel)->Unit),
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items = emptyList<SampleListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return PlanetViewHolder(
                    view = inflater.inflate(R.layout.item_planet_view, parent,false) as View,
                    onPlanetClickListener = onPlanetClickListener
                )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
           holder as PlanetViewHolder
           val planetUiModel = items[position] as PlanetUiModel
           holder.bind(planetUiModel)
    }

    override fun getItemCount(): Int {
    return items.size
    }

}

class PlanetViewHolder (
    view:View,
    private  val onPlanetClickListener: (item:PlanetUiModel)-> Unit
    ):RecyclerView.ViewHolder(view){
    private  val planetImageView: ImageView = view.findViewById(R.id.image_view_planet)
        private  val planetTextView: TextView = view.findViewById(R.id.text_view_planet)
    private  val planetNameTextView: TextView = view.findViewById(R.id.text_view_planet_name)

    fun bind(planetUiModel:PlanetUiModel){
        Glide.with(planetImageView.context).load(planetUiModel.pictureUrl).into(planetImageView)
        planetTextView.text = planetUiModel.description
        planetNameTextView.text = planetUiModel.title
        itemView.setOnClickListener{onPlanetClickListener(planetUiModel)}
    }

    }