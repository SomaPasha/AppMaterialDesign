package space.kuz.appmaterialdesign.iu.iu.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import space.kuz.appmaterialdesign.domain.entity.UniversePageType
import space.kuz.appmaterialdesign.iu.iu.fragment.DailyImageFragment
import space.kuz.appmaterialdesign.iu.iu.fragment.UniversePageFragment

class UniverseStatePagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {

    var items = listOf<UniversePageType>()

    override fun createFragment(position: Int): Fragment {
        val type  = items[position]
        return DailyImageFragment()
    //UniversePageFragment.newInstanse(type)
    }

    override fun getItemCount(): Int {
       return items.size
    }


    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun containsItem(itemId: Long): Boolean {
        return super.containsItem(itemId)
    }

}