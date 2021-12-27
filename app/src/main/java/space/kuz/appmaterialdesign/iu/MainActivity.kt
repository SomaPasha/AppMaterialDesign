package space.kuz.appmaterialdesign.iu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.fragment.DailyImageFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = DailyImageFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_daily, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }
}