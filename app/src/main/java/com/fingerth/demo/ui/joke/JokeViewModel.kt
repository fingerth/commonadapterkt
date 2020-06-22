package com.fingerth.demo.ui.joke

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.fingerth.demo.logic.Repository

class JokeViewModel:ViewModel() {
    private val jokeLiveData = MutableLiveData<Map<String, String>>()

    val jokeData = Transformations.switchMap(jokeLiveData) { map ->

        //Log.v("Fingerth", "Transformations ：switchMap" )
        Repository.getJokePics(map)
    }

    fun searchJokes(map: Map<String, String>) {
        //Log.v("Fingerth", "jokeLiveData ：searchJokes(map)" )
        jokeLiveData.value = map
    }
}