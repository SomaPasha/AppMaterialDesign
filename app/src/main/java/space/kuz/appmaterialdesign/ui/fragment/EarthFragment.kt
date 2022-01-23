package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.api.load
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.EarthImage
import space.kuz.appmaterialdesign.iu.iu.viewmodel.EarthViewModel

class EarthFragment : Fragment() {

    private val viewModel by viewModels<EarthViewModel>()
    private lateinit var earthImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImageDataEarth().observe(this, { earthImage -> renderData(earthImage) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_earth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       earthImageView = view.findViewById(R.id.earth_image_view)
    }

    private fun renderData(earthImage: EarthImage) {
        when (earthImage) {
            is EarthImage.SuccessEarth -> {
                val serverResponseData = earthImage.serverResponseData
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
            is EarthImage.Loading -> {

            }
            is EarthImage.Error -> {

            }
        }
    }
}