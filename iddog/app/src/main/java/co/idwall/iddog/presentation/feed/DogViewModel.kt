package co.idwall.iddog.presentation.feed

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.idwall.iddog.network.DogApiBuilder
import co.idwall.iddog.api.RandomDogResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DogViewModel : ViewModel() {

    private val apiIdDog = DogApiBuilder.getApiInstance()

    private val _localDog = MutableLiveData<RandomDogResponse>()
    val localDog: LiveData<RandomDogResponse>
        get() = _localDog

    fun getRandamDog() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiIdDog.getRandomDog()
                Log.d("Main", "Size: " + response.fileSizeBytes.toString())

                if (response.fileSizeBytes < 400_000) {
                    withContext(Dispatchers.Main) {
                        _localDog.postValue(response)
                    }
                } else {

                    getRandamDog()

                }
            } catch (e: Exception) {
                Log.e("Main", "Error: " + e.message)
            }
        }
    }

    class FeedViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DogViewModel() as T
        }
    }
}