package space.kuz.appmaterialdesign.iu.iu.universescreen

import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.domain.entity.UniversePageType
import space.kuz.appmaterialdesign.iu.iu.adapter.UniverseScreen
import space.kuz.appmaterialdesign.iu.iu.fragment.EarthFragment

class EarthUniverseScreen : UniverseScreen {
    override fun getFragment(): Fragment {
        return EarthFragment()
    }

    override fun getType(): UniversePageType {
        return UniversePageType.Earth
    }

}