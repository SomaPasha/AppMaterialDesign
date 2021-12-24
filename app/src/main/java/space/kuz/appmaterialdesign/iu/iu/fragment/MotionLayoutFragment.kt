package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.*
import android.view.*
import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.databinding.*

class MotionLayoutFragment : Fragment() {
    private lateinit var binding: FragmentMotionLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMotionLayoutBinding.inflate(layoutInflater)
        return  binding.root
    }
}