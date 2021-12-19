package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.databinding.FragmentDailyImageBinding
import space.kuz.appmaterialdesign.databinding.FragmentThemeBinding


class ThemeFragment : Fragment() {

private  lateinit var binding:FragmentThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeBinding.inflate(layoutInflater)
        return binding.root
            //    return inflater.inflate(R.layout.fragment_theme, container, false)
    }

}
