package space.kuz.appmaterialdesign.iu.iu.universescreen

import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.domain.entity.UniversePageType
import space.kuz.appmaterialdesign.iu.iu.adapter.UniverseScreen
import space.kuz.appmaterialdesign.iu.iu.fragment.DailyImageFragment
import space.kuz.appmaterialdesign.iu.iu.fragment.EarthFragment

class DailyUniverseScreen : UniverseScreen {
    override fun getFragment(): Fragment {
        return DailyImageFragment()
    }

    override fun getType(): UniversePageType {
        return UniversePageType.Daily
    }

}