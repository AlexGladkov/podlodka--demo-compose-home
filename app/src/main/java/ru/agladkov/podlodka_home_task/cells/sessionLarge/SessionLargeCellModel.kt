package ru.agladkov.podlodka_home_task.cells.sessionLarge

import ru.agladkov.podlodka_home_task.Session

data class SessionLargeCellModel(
    val date: String,
    val sessions: List<LargeSessionModel>
)

data class LargeSessionModel(
    val authorName: String,
    val time: String,
    val title: String,
    val avatarUrl: String,
    val isFavorite: Boolean
)

fun List<Session>.generateLargeCellModels(): List<SessionLargeCellModel> {
    val mapped = this.groupBy(keySelector = { it.date }, valueTransform = { it.mapToLargeSessionModel() })
    return mapped.map { SessionLargeCellModel(date = it.key, sessions = it.value) }
}

fun Session.mapToLargeSessionModel(isFavorite: Boolean = false) =
    LargeSessionModel(
        authorName = speaker,
        time = timeInterval,
        title = description,
        avatarUrl = imageUrl,
        isFavorite = isFavorite
    )