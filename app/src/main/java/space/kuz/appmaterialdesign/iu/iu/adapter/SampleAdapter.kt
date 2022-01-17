/*package space.kuz.appmaterialdesign.iu.iu.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import coil.target.ImageViewTarget
import com.bumptech.glide.Glide
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.model.*

class SampleAdapter(
    private val onPlanetClickListener:((item:PlanetUiModel)->Unit),
    private val  onAdvertisingClickListener:((item:AdvertisingUiModel)->Unit)
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val  items = emptyList<SampleListItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =  LayoutInflater.from(parent.context)
        return when(viewType){
            viewTypePlanet ->{
                PlanetViewHolder(
                    view = inflater.inflate(R.layout.item_planet_view, parent,false) as View,
                    onPlanetClickListener = onPlanetClickListener
                )
            }
            viewTypeAdvertising ->{
                AdvertisingViewHolder(
                    view = inflater.inflate(R.layout.item_advertising_view, parent,false) as View,
                    onPlanetClickListener = onPlanetClickListener
                )
            }
            else->{
                throw  IllegalArgumentException("Unknown type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}

class PlanetViewHolder (
    view:View,
    private  val onPlanetClickListener: (item:PlanetUiModel)-> Unit
    ):RecyclerView.ViewHolder(view){
        private  val planetImageView: ImageView = view.findViewById(R.id.image_view_planet_image)
    private  val planetNameTextView: TextView = view.findViewById(R.id.text_view_planet_name)

    fun bind(planetUiModel:PlanetUiModel){
        Glide.with(planetImageView.context).load(planetUiModel.pictureUrl).into(planetImageView)
        planetNameTextView.text = planetUiModel.name
        itemView.setOnClickListener{onPlanetClickListener(planetUiModel)}
    }

    }*/