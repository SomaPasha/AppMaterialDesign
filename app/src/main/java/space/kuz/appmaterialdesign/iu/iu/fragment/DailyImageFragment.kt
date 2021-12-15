package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.DailyImage
import space.kuz.appmaterialdesign.iu.iu.viewmodel.DailyImageViewModel

class DailyImageFragment: Fragment() {

 private val viewModel by viewModels<DailyImageViewModel>()

 private lateinit var  dailyImageView: ImageView

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)

  viewModel.getImageData().observe(this,{dailyImage->renderData(dailyImage)})
 }

 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  return inflater.inflate(R.layout.fragment_daily_image,container,false)
 }
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  dailyImageView = view.findViewById(R.id.image_view_nasa_image)
 }


 private  fun renderData(dailyImage: DailyImage){
  when(dailyImage){
   is DailyImage.Success->{
    val serverResponseData =  dailyImage.serverResponseData
    val url = serverResponseData.url

    if (url.isNullOrEmpty()){

    } else{
     dailyImageView.load(url){
      lifecycle(this@DailyImageFragment)
      error(R.drawable.ic_error)
      placeholder(R.drawable.ic_placeholder)
     }
    }
   }
   is DailyImage.Loading->{

   }
   is DailyImage.Error->{

   }
  }
 }
}