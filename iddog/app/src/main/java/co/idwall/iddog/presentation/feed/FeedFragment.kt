package co.idwall.iddog.presentation.feed

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import co.idwall.iddog.R
import co.idwall.iddog.databinding.FragmentFeedBinding
import co.idwall.iddog.domain.ApiRequest
import co.idwall.iddog.domain.BASE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class FeedFragment : Fragment(R.layout.fragment_feed) {

    lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFeedBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        makeApiRequest()
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

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){

            try {

                val response = api.getRandomDog()
                Log.d("Main","Size: "+ response.fileSizeBytes.toString())

                if (response.fileSizeBytes < 400_000){
                    withContext(Dispatchers.Main){
                        Glide.with(requireContext()).load(response.url).into(binding.ivRandomDog)
                        binding.ivRandomDog.visibility = View.VISIBLE
                    }
                } else {
                    makeApiRequest()
                }
            } catch (e: Exception) {
                Log.e("Main","Error: " + e.message)
            }
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