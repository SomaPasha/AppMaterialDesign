package space.kuz.appmaterialdesign.iu.iu.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.UniversePageType
import space.kuz.appmaterialdesign.iu.iu.adapter.UniverseStatePagerAdapter
import space.kuz.appmaterialdesign.transformer.ZoomOutPageTransformer

class UniverseFragment: Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_universe, container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.view_pager_universe)
        val adapter = UniverseStatePagerAdapter(this)

        adapter.items = UniversePageType.values().toList()
        viewPager.adapter = adapter
        viewPager.setPageTransformer(ZoomOutPageTransformer() )

        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
             //   Toast.makeText(context, "onPageScrolled Позиция ${position} Позиция2 ${positionOffset}",Toast.LENGTH_SHORT).show()
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
           //     Toast.makeText(context, "onPageSelected Позиция ${position}",Toast.LENGTH_SHORT).show()
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
               // Toast.makeText(context, "onPageScrollStateChanged Состояние ${state}",Toast.LENGTH_SHORT).show()
            }
        })

        tabLayout = view.findViewById(R.id.tab_layout_universe)

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            val type = adapter.items[position]
            tab.text = type.name
            tab.icon = ContextCompat.getDrawable(requireContext(),R.drawable.fly_image)
        }.attach()
    }


}