package com.example.covidapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covidapi.network.Covid
import com.example.covidapi.network.GameApi
import kotlinx.coroutines.launch

enum class CovidApiStatus { LOADING, ERROR, DONE }

class CovidViewModel : ViewModel(){
    private val _status = MutableLiveData<CovidApiStatus>()
    val status: LiveData<CovidApiStatus> = _status

    private val _games = MutableLiveData<MutableList<Covid>>()
    val games: MutableLiveData<MutableList<Covid>> = _games

    private val _game = MutableLiveData<Covid>()
    val covid: LiveData<Covid> = _game

    fun getGameList() {
        viewModelScope.launch {
        _status.value = CovidApiStatus.LOADING
            try {
                _games.value = GameApi.retrofitServiceApi.getGames()
                _status.value = CovidApiStatus.DONE
            } catch (e: Exception) {
                _games.value = mutableListOf()
                _status.value = CovidApiStatus.ERROR
            }
        }
    }

    fun onGameClicked(covid: Covid) {
        _game.value = covid
    }

}