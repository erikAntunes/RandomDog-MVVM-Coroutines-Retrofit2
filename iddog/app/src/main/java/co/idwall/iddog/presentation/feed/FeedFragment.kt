package co.idwall.iddog.presentation.feed

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import co.idwall.iddog.R
import co.idwall.iddog.databinding.FragmentFeedBinding

class FeedFragment : Fragment(R.layout.fragment_feed) {

    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        backgroundAnimation()

        binding.refreshDogActionButton.setOnClickListener {
            binding.refreshDogActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()
            makeApiRequest()
            binding.ivRandomDog.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun makeApiRequest() {

    }

    private fun backgroundAnimation() {
        val animationDrawable: AnimationDrawable = binding.rlLayout.background as AnimationDrawable

        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }
}