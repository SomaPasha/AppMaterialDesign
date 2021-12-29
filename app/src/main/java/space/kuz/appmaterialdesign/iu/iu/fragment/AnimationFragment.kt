package space.kuz.appmaterialdesign.iu.iu.fragment

import android.os.Bundle
import android.transition.*
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment()
{
    private lateinit var binding :FragmentAnimationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
           setTransitionAnimationSamples()
    }


    private  val hideTextSampleTransition by lazy {
        val transitionSet = TransitionSet()
        transitionSet.duration = 1000
        transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

        transitionSet.addTransition(Fade(Fade.OUT))
        transitionSet.addTransition(ChangeBounds())
        transitionSet
    }

    private  fun setTransitionAnimationSamples(){
    binding.transitionAnimationSampleButton.setOnClickListener {
     // val contraintSetSampleTextViewIsVisible = binding.textViewAnimation.isVisible
    //        val transitionSet = if ( contraintSetSampleTextViewIsVisible){
   //            hideTextSampleTransition
    //        }else{
    //        }

        TransitionManager.beginDelayedTransition(binding.animationLinearLayout)
        binding.textViewAnimation.isVisible =  !binding.textViewAnimation.isVisible

    }
    }
}

