package space.kuz.appmaterialdesign.iu.iu.fragment

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.FragmentDailyImageBinding
import space.kuz.appmaterialdesign.databinding.FragmentEarthBinding
import space.kuz.appmaterialdesign.databinding.FragmentUniverseBinding
import space.kuz.appmaterialdesign.domain.entity.DailyImage
import space.kuz.appmaterialdesign.iu.iu.Const
import space.kuz.appmaterialdesign.iu.iu.viewmodel.DailyImageViewModel
import java.lang.IllegalStateException

class EarthFragment : Fragment() {

    private val viewModel by viewModels<DailyImageViewModel>()
    private lateinit var binding : FragmentEarthBinding


    private lateinit var earthImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewModel.getImageData().observe(this, { dailyImage -> renderData(dailyImage) })
        viewModel.getImageDataEarth().observe(this, { dailyImage -> renderData2(dailyImage) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEarthBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       earthImageView = view.findViewById(R.id.earth_image_view)
    }

    private fun renderData2(dailyImage: DailyImage) {
        when (dailyImage) {
            is DailyImage.SuccessEarth -> {
                val serverResponseData = dailyImage.serverResponseData
                val url: String?
                url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                } else {
                    earthImageView.load(url) {
                        lifecycle(this@EarthFragment)
                        error(R.drawable.ic_error)
                        placeholder(R.drawable.ic_placeholder)
                    }
                }
            }
            is DailyImage.Loading -> {

            }
            is DailyImage.Error -> {

            }
        }
    }
}