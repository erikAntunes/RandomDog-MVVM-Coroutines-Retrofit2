package co.idwall.iddog.presentation.login

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.idwall.iddog.R
import co.idwall.iddog.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backgroundAnimation()
        setupListenners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentLoginBinding.inflate(layoutInflater)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private fun setupListenners(){
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_feed)
        }
    }

    private fun backgroundAnimation() {
        val animationDrawable: AnimationDrawable = binding.rlLoginLayout.background as AnimationDrawable
        animationDrawable.apply {
            setEnterFadeDuration(1000)
            setExitFadeDuration(3000)
            start()
        }
    }
}