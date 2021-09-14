package co.idwall.iddog.presentation.feed

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import co.idwall.iddog.R
import co.idwall.iddog.databinding.FragmentFeedBinding
import com.bumptech.glide.Glide

class DogFragment : Fragment(R.layout.fragment_feed) {

    private lateinit var dogViewModel: DogViewModel

    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentFeedBinding.inflate(layoutInflater)

        dogViewModel = ViewModelProvider(
            this, DogViewModel.FeedViewModelFactory()
        ).get(DogViewModel::class.java)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        backgroundAnimation()
        setupListeners()
        dogViewModel.getRandamDog()
    }

    private fun setupObservables() {
        with(dogViewModel) {

            localDog.observe(viewLifecycleOwner, Observer {
                setDogBackground(it.url)
            })
        }
    }

    private fun setDogBackground(dogUrl: String) {
        Glide.with(requireContext()).load(dogUrl).into(binding.ivRandomDog)
        binding.ivRandomDog.visibility = View.VISIBLE
    }

    private fun setupListeners() {

        binding.refreshDogActionButton.setOnClickListener {
            binding.ivRandomDog.visibility = View.GONE
            binding.refreshDogActionButton.animate().apply {
                rotationBy(360f)
                duration = 1000
            }.start()

            dogViewModel.getRandamDog()
        }
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