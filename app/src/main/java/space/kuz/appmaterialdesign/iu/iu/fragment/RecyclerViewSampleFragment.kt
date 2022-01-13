package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.R

class RecyclerViewSampleFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_sample,container,false)
    }



}