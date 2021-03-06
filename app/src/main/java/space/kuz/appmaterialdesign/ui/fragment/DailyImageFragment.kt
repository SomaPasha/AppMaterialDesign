package space.kuz.appmaterialdesign.ui.fragment

import android.content.*
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.*
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.*
import kotlinx.coroutines.flow.callbackFlow
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.domain.entity.DailyImage
import space.kuz.appmaterialdesign.ui.*
import space.kuz.appmaterialdesign.ui.viewmodel.DailyImageViewModel

class DailyImageFragment : Fragment() {
    private val appThemeSaved by lazy { AppThemePreferenceDelegate() }

    private val viewModel by viewModels<DailyImageViewModel>()

    private lateinit var dailyImageView: ImageView
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var inputLayoutWiki: TextInputLayout
    private lateinit var inputEditTextWiki: TextInputEditText
    private lateinit var bottomSheetDescription: TextView
    private lateinit var bottomSheetDescriptionHeader: TextView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var chipHd: Chip

    private var checkHd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getImageData().observe(this, { dailyImage -> renderData(dailyImage) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dailyImageView = view.findViewById(R.id.image_view_nasa_image)
        bottomSheetDescription = view.findViewById(R.id.bottom_sheet_description)
        bottomSheetDescriptionHeader = view.findViewById(R.id.bottom_sheet_description_header)
        inputLayoutWiki = view.findViewById(R.id.input_layout_wiki)
        inputEditTextWiki = view.findViewById(R.id.input_edit_text_wiki)

        chipHd = view.findViewById(R.id.chip_hd)
        val image= viewModel.getImageData().value as DailyImage
        chipHd.setOnClickListener {
            checkHd = !checkHd
            viewModel.sendServerRequest()
            renderData(image)
            //viewModel.getImageData().observe(viewLifecycleOwner, {dailyImage -> renderData(dailyImage)})
        }

        fabAdd = view.findViewById(R.id.fab)
        fabAdd.setOnClickListener {
            val newTheme =appThemeSaved.savedThemeToStyleId(appThemeSaved.getSavedTheme(requireActivity()))
                appThemeSaved.setSavedTheme(requireActivity(),newTheme)
            requireActivity().recreate()
        }

        inputLayoutWiki.setEndIconOnClickListener {
            startActivity(viewModel.openWiki(inputEditTextWiki.text.toString()))
        }

        startBottomSheetBehavior(view)
        setBottomAppBar(view)
    }

    private fun startBottomSheetBehavior(view: View) {
        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        var callback = object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> Toast.makeText(
                        context, "STATE_DRAGGING", Toast.LENGTH_SHORT
                    ).show()
                    BottomSheetBehavior.STATE_COLLAPSED -> Toast.makeText(
                        context, "STATE_COLLAPSED", Toast.LENGTH_SHORT
                    ).show()
                    BottomSheetBehavior.STATE_EXPANDED -> Toast.makeText(
                        context, "STATE_EXPANDED", Toast.LENGTH_SHORT
                    ).show()
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> Toast.makeText(
                        context, "STATE_HALF_EXPANDED", Toast.LENGTH_SHORT
                    ).show()
                    BottomSheetBehavior.STATE_HIDDEN -> Toast.makeText(
                        context, "STATE_HIDDEN", Toast.LENGTH_SHORT
                    ).show()
                    BottomSheetBehavior.STATE_SETTLING -> Toast.makeText(
                        context, "STATE_SETTLING", Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                //todo
            }
        }

        bottomSheetBehavior.addBottomSheetCallback(callback)
    }

   private  fun renderData(dailyImage: DailyImage) {
        when (dailyImage) {
            is DailyImage.Success -> {
                val serverResponseData = dailyImage.serverResponseData
                val planation = serverResponseData.explanation
                val planationHead = serverResponseData.title

                bottomSheetDescription.text = planation
                bottomSheetDescriptionHeader.text = planationHead

                val url = if (checkHd) {
                    serverResponseData.url
                } else {
                    serverResponseData.hdurl
                }

                if (!url.isNullOrEmpty()) {
                    dailyImageView.load(url) {
                        lifecycle(this@DailyImageFragment)
                        error(R.drawable.ic_error)
                        placeholder(R.drawable.ic_placeholder)
                    }
                }
            }
        }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_search -> Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()

            android.R.id.home -> {
                val activity = requireActivity()
                BottomNavigationDrawerFragment().show(activity.supportFragmentManager, "tag")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        val context = requireContext() as AppCompatActivity
        context.setSupportActionBar(view.findViewById(R.id.bottom_app_bar))
        setHasOptionsMenu(true)
    }
}