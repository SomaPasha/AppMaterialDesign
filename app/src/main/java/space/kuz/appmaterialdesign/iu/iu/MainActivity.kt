package space.kuz.appmaterialdesign.iu.iu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.ActivityMainBinding
import space.kuz.appmaterialdesign.iu.iu.fragment.DailyImageFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = DailyImageFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_daily, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()

    }
}