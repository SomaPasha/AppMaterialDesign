package space.kuz.appmaterialdesign.iu.iu.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.FragmentDailyImageBinding
import space.kuz.appmaterialdesign.domain.entity.DailyImage
import space.kuz.appmaterialdesign.iu.iu.viewmodel.DailyImageViewModel

class DailyImageFragment: Fragment() {

 private val viewModel by viewModels<DailyImageViewModel>()
 private  lateinit var binding: FragmentDailyImageBinding
 private lateinit var  dailyImageView: ImageView
 private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
 private lateinit var bottomSheetDescription: TextView
 private lateinit var bottomSheetDescriptionHeader: TextView

 override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)

  viewModel.getImageData().observe(this,{dailyImage->renderData(dailyImage)})

 }

 override fun onCreateView(
  inflater: LayoutInflater,
  container: ViewGroup?,
  savedInstanceState: Bundle?
 ): View? {
  binding = FragmentDailyImageBinding.inflate(layoutInflater)
  return binding.root
  //return inflater.inflate(R.layout.fragment_daily_image,container,false)
 }
 override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  super.onViewCreated(view, savedInstanceState)
  dailyImageView = view.findViewById(R.id.image_view_nasa_image)
  bottomSheetDescription = view.findViewById(R.id.bottom_sheet_description)
  bottomSheetDescriptionHeader =view.findViewById(R.id.bottom_sheet_description_header)
  binding.inputLayoutWiki.setEndIconOnClickListener {
   val intent = Intent(Intent.ACTION_VIEW)
   val url = "https://en.wikipedia.org/wiki/${ binding.inputEditTextWiki.text.toString()}"
   val uri = Uri.parse(url)
   intent.data = uri
   startActivity(intent)
  }
  startBottomSheetBehavior(view)
 }

 private  fun startBottomSheetBehavior(view: View){
  setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
  bottomSheetBehavior.addBottomSheetCallback(object :
   BottomSheetBehavior.BottomSheetCallback() {
   override fun onStateChanged(bottomSheet: View, newState: Int) {
    when (newState) {

     BottomSheetBehavior.STATE_DRAGGING ->  Toast.makeText(context,"STATE_DRAGGING",Toast.LENGTH_SHORT).show()
     BottomSheetBehavior.STATE_COLLAPSED ->  Toast.makeText(context,"STATE_COLLAPSED",Toast.LENGTH_SHORT).show()
     BottomSheetBehavior.STATE_EXPANDED ->  Toast.makeText(context,"STATE_EXPANDED",Toast.LENGTH_SHORT).show()
     BottomSheetBehavior.STATE_HALF_EXPANDED ->  Toast.makeText(context,"STATE_HALF_EXPANDED",Toast.LENGTH_SHORT).show()
     BottomSheetBehavior.STATE_HIDDEN ->  Toast.makeText(context,"STATE_HIDDEN",Toast.LENGTH_SHORT).show()
     BottomSheetBehavior.STATE_SETTLING ->  Toast.makeText(context,"STATE_SETTLING",Toast.LENGTH_SHORT).show()
    }
   }

   override fun onSlide(bottomSheet: View, slideOffset: Float) {
   }
  })

 }
 private  fun renderData(dailyImage: DailyImage){
  when(dailyImage){
   is DailyImage.Success->{

    val serverResponseData =  dailyImage.serverResponseData
    val planation = serverResponseData.explanation
    val planationHead = serverResponseData.title
    bottomSheetDescription.text = planation
    bottomSheetDescriptionHeader.text= planationHead
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
 private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
  bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
  bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
 }







}