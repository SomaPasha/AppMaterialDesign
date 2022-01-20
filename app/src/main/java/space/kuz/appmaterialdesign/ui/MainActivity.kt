package space.kuz.appmaterialdesign.ui

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.*
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.ui.fragment.DailyImageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    val saveTheme = "SAVE_THEME"

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = getPreferences(MODE_PRIVATE)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
        setTheme(sharedPreferences.getInt(saveTheme,0))

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = DailyImageFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_daily, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

}