package space.kuz.appmaterialdesign.iu.iu.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import space.kuz.appmaterialdesign.R
import space.kuz.appmaterialdesign.iu.iu.viewmodel.TextSampleViewModel
import java.lang.IllegalArgumentException

class TextSampleFragment:Fragment() {
    private val viewModel by viewModels<TextSampleViewModel>()

    private lateinit var spannableSampleTextView: TextView
    private lateinit var radioGroup: RadioGroup
    private  lateinit var boldTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text_sample, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        boldTextView = view.findViewById(R.id.text_view_title_bold)
        spannableSampleTextView = view.findViewById(R.id.text_view_spanned_text)
        radioGroup = view.findViewById(R.id.radio_group_span)

        var customFont = Typeface.createFromAsset(requireActivity().assets, "fonts/Boncegro.otf")
        boldTextView.setTypeface(customFont)

        viewModel.getSpannableText().observe(viewLifecycleOwner){text ->
            spannableSampleTextView.text = text
        }

        viewModel.getMessageText().observe(viewLifecycleOwner){text ->
           Toast.makeText(requireContext(),text,Toast.LENGTH_SHORT).show()
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.radio_button_background_color_span -> viewModel.onBackgroundColorSpanClicked()
                R.id.radio_button_clickable_span -> viewModel.onClickableSpanClicked()
                R.id.radio_button_image_span-> viewModel.onImageSpanClicked(requireContext())
                else -> throw IllegalArgumentException("unknow id")
            }
        }

        spannableSampleTextView.movementMethod = LinkMovementMethod.getInstance()

        val radioButton:RadioButton = view.findViewById(R.id.radio_button_clickable_span)
        radioButton.isChecked = true
    }
}