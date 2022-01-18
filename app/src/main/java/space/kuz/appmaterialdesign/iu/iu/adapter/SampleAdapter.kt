package space.kuz.appmaterialdesign.iu.iu.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.model.*
import java.lang.IllegalStateException

private const val viewTypePlanet = 0
private const val viewTypeAdvertising  = 1

class SampleAdapter(
    private val onPlanetClickListener:((item:PlanetUiModel)->Unit),
    private val  onAdvertisingClickListener:((item:AdvertisingUiModel)->Unit)
):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var items = emptyList<SampleListItem>()
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
                    onAdvertisingClickListener = onAdvertisingClickListener
                )
            }
            else->{
                throw  IllegalArgumentException("Unknown type")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if(getItemViewType(position) == viewTypePlanet){
           holder as PlanetViewHolder
           val planetUiModel = items[position] as PlanetUiModel
           holder.bind(planetUiModel)
       }else{
           holder as AdvertisingViewHolder
           val advertisingUiModel = items[position] as AdvertisingUiModel
           holder.bind(advertisingUiModel)
       }
    }

    override fun getItemCount(): Int {
    return items.size
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when(item){
          is PlanetUiModel -> viewTypePlanet
          is AdvertisingUiModel -> viewTypeAdvertising
          else -> throw IllegalStateException("unknow view type")
        }
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

    }



class AdvertisingViewHolder (
    view:View,
    private  val onAdvertisingClickListener: (item: AdvertisingUiModel) -> Unit
):RecyclerView.ViewHolder(view){
    private  val advertisingTitleTextView: TextView = view.findViewById(R.id.text_view_advertising_title)
    private  val advertisingDescriptionTextView: TextView = view.findViewById(R.id.text_view_advertising_description)

    fun bind(advertisingUiModel:AdvertisingUiModel) {
        advertisingTitleTextView.text = advertisingUiModel.title
      advertisingDescriptionTextView.text = advertisingUiModel.description
        itemView.setOnClickListener{onAdvertisingClickListener(advertisingUiModel)}
    }

}