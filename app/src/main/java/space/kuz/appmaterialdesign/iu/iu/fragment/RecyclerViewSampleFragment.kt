package space.kuz.appmaterialdesign.iu.iu.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import okhttp3.internal.notify
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.iu.adapter.SampleAdapter
import space.kuz.appmaterialdesign.iu.iu.viewmodel.RecyclerViewSampleViewModel

class RecyclerViewSampleFragment:Fragment() {

    private val viewModel by viewModels<RecyclerViewSampleViewModel>()

    private  lateinit var recyclerView: RecyclerView

    private  val adapter by lazy {
        SampleAdapter(
        onPlanetClickListener = {planet-> viewModel.onPlanetClick(planet)} ,
        onAdvertisingClickListener  ={adversting-> viewModel.onAdvertisingClick(adversting)}
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler_view_sample,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycle_view_sample)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


       // val callback  = ItemDragTouchHelperCallback(
        // onItemMove = { from, to ->
        // viewModel.onItemMoved(from, to)
        //},
        // onItemSwiped = {position -> viewModel.onItemRemoved(position),
        //)
        // val touchHelper =ItemTouchHelper(callback)
        // touchHelper.attachToRecyclerView(recycleView)
       observeViewModel()
        viewModel.loadData()
    }
    @SuppressLint("NotifyDataChanged")
    private fun observeViewModel() {
       viewModel.getItems().observe(viewLifecycleOwner){
           items->
           adapter.items = items
           adapter.notifyDataSetChanged()
       }

        viewModel.getMessage().observe(viewLifecycleOwner){message ->
            Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
        }
    }

}