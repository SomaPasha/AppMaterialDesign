package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.*
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.*
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.iu.adapter.*
import space.kuz.appmaterialdesign.iu.iu.viewmodel.UniverseViewModel
import space.kuz.appmaterialdesign.transformer.DepthPageTransformer

class UniverseFragment : Fragment() {

    private val viewModel by viewModels<UniverseViewModel>()
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager_universe)
        val adapter = UniverseStatePagerAdapter(this)
        val screens = viewModel.getFragmentUniverse().value as List<UniverseScreen>
        adapter.items = screens
        viewPager.adapter = adapter
        viewPager.setPageTransformer(DepthPageTransformer())

        tabLayout = view.findViewById(R.id.tab_layout_universe)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val type = adapter.items[position]
            tab.text = type.getType().name
            tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.fly_image)
        }.attach()
    }
}