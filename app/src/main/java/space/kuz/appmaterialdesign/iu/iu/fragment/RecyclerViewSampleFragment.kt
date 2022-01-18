package space.kuz.appmaterialdesign.iu.iu.fragment

import android.annotation.SuppressLint
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.iu.adapter.SampleAdapter
import space.kuz.appmaterialdesign.iu.iu.diffubtil.SampleDiffUtil
import space.kuz.appmaterialdesign.iu.iu.viewmodel.RecyclerViewSampleViewModel
import space.kuz.appmaterialdesign.transformer.ItemDragTouchHelperCallback

class RecyclerViewSampleFragment:Fragment() {

    private val viewModel by viewModels<RecyclerViewSampleViewModel>()

    private  lateinit var recyclerView: RecyclerView
    private  lateinit var newButton: ImageButton
    private  lateinit var titleEditText: EditText
    private  lateinit var descriptionEditText: EditText

    private  val adapter by lazy {
        SampleAdapter(
        onBookClickListener = {book-> viewModel.onBookClick(book)} ,
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
        newButton = view.findViewById(R.id.new_button)
        titleEditText = view.findViewById(R.id.title_edit_text)
        descriptionEditText = view.findViewById(R.id.description_edit_text)
        newButton.setOnClickListener {
            viewModel.addItem(titleEditText.text.toString(),descriptionEditText.text.toString() )
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val callback  = ItemDragTouchHelperCallback(
         onItemMove = { from, to ->
         viewModel.onItemMoved(from, to)
        },
         onItemSwiped = {position -> viewModel.onItemRemoved(position)},
        )
         val touchHelper =ItemTouchHelper(callback)
         touchHelper.attachToRecyclerView(recyclerView)


       observeViewModel()
    }
    @SuppressLint("NotifyDataChanged")
    private fun observeViewModel() {
       viewModel.getItems().observe(viewLifecycleOwner){
           items->

           val sampleDiffUtil = SampleDiffUtil(
               oldList = adapter.items,
                newList = items,
           )
           val sampleDiffResult = DiffUtil.calculateDiff(sampleDiffUtil)
           adapter.items = items
           sampleDiffResult.dispatchUpdatesTo(adapter)
       }

        viewModel.getMessage().observe(viewLifecycleOwner){message ->
            Toast.makeText(requireContext(), message,Toast.LENGTH_SHORT).show()
        }
    }

}