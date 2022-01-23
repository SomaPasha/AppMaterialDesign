package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.UniversePageType

private const val pageTypeKey = "pageTypeKey"

class UniversePageFragment : Fragment() {

    private val universePageType by lazy {
        requireArguments().getSerializable(pageTypeKey) as UniversePageType
    }
    private lateinit var titleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTextView = view.findViewById(R.id.text_view_universe_page_title)
        titleTextView.text = universePageType.name
    }

    companion object{

        fun newInstanse(universePageType: UniversePageType):UniversePageFragment{
            val bundle = Bundle()
            bundle.putSerializable(pageTypeKey,universePageType)
            val fragment = UniversePageFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}