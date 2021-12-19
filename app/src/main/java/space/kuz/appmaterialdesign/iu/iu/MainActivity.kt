package space.kuz.appmaterialdesign.iu.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.ActivityMainBinding
import space.kuz.appmaterialdesign.iu.iu.fragment.DailyImageFragment

class MainActivity : AppCompatActivity(){
lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Const.currentTheme);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment  = DailyImageFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_daily, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()


    }
}