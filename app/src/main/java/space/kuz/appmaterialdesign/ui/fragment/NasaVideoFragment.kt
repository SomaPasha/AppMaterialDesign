package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.NasaVideoSealed
import space.kuz.appmaterialdesign.iu.iu.viewmodel.NasaVideoViewModel


class NasaVideoFragment :Fragment() {
    private val viewModel by viewModels<NasaVideoViewModel>()
    private lateinit var nasaWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getVideoData().observe(this, { nasaVideo -> renderData(nasaVideo) })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nasa_video, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      nasaWebView = view.findViewById(space.kuz.appmaterialdesign.R.id.nasa_web_view)
        nasaWebView.settings.javaScriptEnabled =true
    }


    private fun renderData(nasaVideo: NasaVideoSealed) {
        when (nasaVideo) {
            is NasaVideoSealed.Success -> {
                val serverResponseData = nasaVideo.serverResponseData
                val url: String?
                url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                } else {
                    nasaWebView.loadUrl(url)
                }
            }
            is NasaVideoSealed.Loading -> {

            }
            is NasaVideoSealed.Error -> {

            }
        }
    }
}