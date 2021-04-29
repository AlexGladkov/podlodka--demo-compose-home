package ru.agladkov.podlodka_home_task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.agladkov.podlodka_home_task.cells.sessionLarge.LargeSessionModel
import ru.agladkov.podlodka_home_task.cells.sessionLarge.SessionLargeCellModel
import ru.agladkov.podlodka_home_task.cells.sessionLarge.generateLargeCellModels
import ru.agladkov.podlodka_home_task.cells.sessionSmall.SessionCellModel
import ru.agladkov.podlodka_home_task.cells.sessionSmall.mapToSessionCellModel

data class MainViewState(
    val favorites: List<SessionCellModel> = emptyList(),
    val sessionsList: List<SessionLargeCellModel> = emptyList()
)

class MainViewModel: ViewModel() {

    private val _state: MutableLiveData<MainViewState> = MutableLiveData(MainViewState())
    val state: LiveData<MainViewState> = _state
    val needToReload = false

    fun addToFavorite(model: LargeSessionModel, date: String) {
        val oldFavorites = state.value?.favorites ?: emptyList()

        _state.postValue(_state.value?.copy(
            favorites = ArrayList<SessionCellModel>(oldFavorites.size + 1).apply {
                this.addAll(oldFavorites)
                this.add(model.mapToSessionCellModel(date))
            }
        ))
    }

    fun parseMockData() {
        _state.postValue(MainViewState(sessionsList = MockSessions.generateLargeCellModels()))
    }
}