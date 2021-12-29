package space.kuz.appmaterialdesign.iu.iu.fragment

import android.animation.*
import android.graphics.Color
import android.os.Bundle
import android.transition.*
import android.view.*
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.addListener
import androidx.core.app.ActivityCompat.recreate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import space.kuz.appmaterialdesign.databinding.FragmentAnimationBinding
import java.util.*

class AnimationFragment : Fragment()
{
    private lateinit var binding :FragmentAnimationBinding
    private lateinit var  animator: ObjectAnimator
    private lateinit var  animatorValue: ValueAnimator
    private lateinit var  animatorSet: AnimatorSet

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
           setObjectAnimatorSample()
           setValueAnimatorSample()
        binding.valueRgbAnimationSampleButton.setOnClickListener {
            createArgbAnimator()
        }
           setAnimationSetExample()
        setConstraintExample()
        binding.cancelButton.setOnClickListener {
            try {
                animatorSet.cancel()
                animator.cancel()
                animatorValue.cancel()
            }catch (e:Exception){
                try {
                    animator.cancel()
                    animatorSet.cancel()
                    animatorValue.cancel()
                }catch (e:Exception){
                    try {
                        animatorValue.cancel()
                        animator.cancel()
                        animatorSet.cancel()
                    }catch (e:Exception){

                    }
                }
            }

        }
    }



    private  fun setTransitionAnimationSamples(){
    binding.transitionAnimationSampleButton.setOnClickListener {
      val contraintSetSampleTextViewIsVisible = binding.textViewAnimation.isVisible
            val transitionSet = if ( contraintSetSampleTextViewIsVisible){
               hideTextSampleTransition
            }else{
                showTextSampleTransition
           }
        TransitionManager.beginDelayedTransition(binding.animationLinearLayout, transitionSet)
        TransitionManager.beginDelayedTransition(binding.animationLinearLayout)
        binding.textViewAnimation.isVisible =  !binding.textViewAnimation.isVisible
    }
    }
    private  val hideTextSampleTransition by lazy {
        val transitionSet = TransitionSet()
        transitionSet.duration = 1000
        transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

        transitionSet.addTransition(Fade(Fade.OUT))
        transitionSet.addTransition(ChangeBounds())
        transitionSet
    }
    private  val showTextSampleTransition by lazy {
        val transitionSet = TransitionSet()
        transitionSet.duration = 1000
        transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

        transitionSet.addTransition(Fade(Fade.IN))
        transitionSet.addTransition(ChangeBounds())
        transitionSet
    }

    private fun setObjectAnimatorSample(){
        binding.objectAnimationSampleButton.setOnClickListener {
             animator = ObjectAnimator.ofFloat(binding.textViewAnimation, View.ALPHA,0f,1f)

            animator.repeatMode = ValueAnimator.REVERSE
           animator.repeatCount = ValueAnimator.INFINITE
            animator.duration = 1_500
            animator.addListener(
                onEnd = {},
                onStart ={},
                onRepeat ={},
                onCancel = {}
            )

            animator.start()
        }
    }

    private fun setValueAnimatorSample(){
        binding.valueAnimationSampleButton.setOnClickListener {
            animatorValue = createValueAnimator()
            animatorValue.start()
        }
    }
    private fun createValueAnimator():ValueAnimator{
        val animator = ValueAnimator.ofFloat(0f,1f)

        animator.repeatMode = ValueAnimator.REVERSE
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Float
            binding.textViewAnimation.alpha = value
        }

        animator.addListener(
            onEnd = {},
            onStart ={},
            onRepeat ={},
            onCancel = {}
        )
        return animator
    }

    private  fun createArgbAnimator():ValueAnimator{
         animatorValue = ValueAnimator.ofArgb(Color.BLUE,Color.RED)

        animatorValue.repeatMode = ValueAnimator.REVERSE
        animatorValue.repeatCount = ValueAnimator.INFINITE
        animatorValue.duration = 3_000
        animatorValue.addUpdateListener { valueAnimator ->
            val value = valueAnimator.animatedValue as Int
            binding.textViewAnimation.setTextColor(value)
        }

        animatorValue.addListener(
            onEnd = {},
            onStart ={},
            onRepeat ={},
            onCancel = {}
        )
        animatorValue.start()
        return animatorValue
    }

    private fun  setAnimationSetExample(){
        binding.animatorExampleButton.setOnClickListener {
            animatorSet = AnimatorSet()
            val animators = listOf( createArgbAnimator(), createValueAnimator())
            animatorSet.playTogether(animators)
            animatorSet.start()
        }
    }

    private fun setConstraintExample(){
        binding.constraintExampleButton.setOnClickListener {

        }
    }


}

