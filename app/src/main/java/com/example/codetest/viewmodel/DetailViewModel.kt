package com.example.codetest.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.codetest.model.DogBreed
import com.example.codetest.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }
    }
}