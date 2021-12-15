package space.kuz.appmaterialdesign.iu.iu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.ActivityMainBinding
import space.kuz.appmaterialdesign.iu.iu.fragment.DailyImageFragment

class MainActivity : AppCompatActivity(){
lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
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