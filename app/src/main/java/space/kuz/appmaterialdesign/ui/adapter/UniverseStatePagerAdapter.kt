package space.kuz.appmaterialdesign.iu.iu.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import space.kuz.appmaterialdesign.domain.entity.UniversePageType


interface  UniverseScreen{
    fun getFragment(): Fragment
    fun getType():UniversePageType
}
class UniverseStatePagerAdapter(fragment:Fragment):FragmentStateAdapter(fragment) {

    var items = listOf<UniverseScreen>()

    override fun createFragment(position: Int): Fragment {
        val screen  = items[position]
        return screen.getFragment()
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