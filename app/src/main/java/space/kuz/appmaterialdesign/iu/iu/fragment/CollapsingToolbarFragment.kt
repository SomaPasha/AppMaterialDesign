package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import coil.api.load
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.*

class CollapsingToolbarFragment: Fragment() {
    private lateinit var binding:FragmentCollapsingToolbarBinding
    private lateinit var imageToolbar:ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollapsingToolbarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageToolbar  = view.findViewById(R.id.image_view_expanded_toolbar_background)
        var url = "https://focus.ua/static/storage/thumbs/740x375/4/06/ac1f5121-723c57083305190737254934fe2a4064.jpeg?v=3358_1"
        imageToolbar.load(url)
    }
}