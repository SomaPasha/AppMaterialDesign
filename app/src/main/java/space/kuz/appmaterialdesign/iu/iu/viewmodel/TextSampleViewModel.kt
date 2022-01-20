package space.kuz.appmaterialdesign.iu.iu.viewmodel

import android.content.Context
import android.graphics.Color
import android.text.Spanned
import android.text.style.*
import android.view.View
import androidx.core.text.toSpannable
import androidx.lifecycle.*
import space.kuz.appmaterialdesign.R

private  const val initialTextForSpan = "Измени это - |Хи-хи|"

class TextSampleViewModel :ViewModel() {

    private val spannableTextLiveData = MutableLiveData<CharSequence>()
    private val messageTextLiveData = MutableLiveData<String>()

    fun getSpannableText():LiveData<CharSequence>{
        return  spannableTextLiveData
    }
    fun getMessageText():LiveData<String>{
        return  messageTextLiveData
    }

    fun onBackgroundColorSpanClicked(){
        val spannable = initialTextForSpan.toSpannable()
        spannable.setSpan(BackgroundColorSpan(Color.CYAN),13,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTextLiveData.value = spannable
    }

    fun onClickableSpanClicked(){
        val clickableSpan = object :ClickableSpan(){
            override fun onClick(widget: View) {
              messageTextLiveData.value = "Span Clicked"
            }

        }
        val spannable = initialTextForSpan.toSpannable()
        spannable.setSpan(clickableSpan, 13,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTextLiveData.value = spannable
    }

    fun onImageSpanClicked (context:Context){
        val imageSpan = ImageSpan(context, R.drawable.fly_image)

        val spannable = initialTextForSpan.toSpannable()
        spannable.setSpan(imageSpan, 13,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableTextLiveData.value = spannable
    }


}