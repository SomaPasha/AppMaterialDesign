package space.kuz.appmaterialdesign.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.*
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.iu.fragment.UniverseFragment
import space.kuz.appmaterialdesign.ui.fragment.DailyImageFragment

class MainActivity : AppCompatActivity() {

    private val appThemeSaved by lazy { AppThemePreferenceDelegate(this) }

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(appThemeSaved.getSavedTheme())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = UniverseFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_daily, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}