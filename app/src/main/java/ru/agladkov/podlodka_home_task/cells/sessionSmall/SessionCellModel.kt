package ru.agladkov.podlodka_home_task.cells.sessionSmall

import ru.agladkov.podlodka_home_task.Session
import ru.agladkov.podlodka_home_task.cells.sessionLarge.LargeSessionModel

data class SessionCellModel(
    val time: String,
    val date: String,
    val authorName: String,
    val title: String
)

fun LargeSessionModel.mapToSessionCellModel(date: String) =
    SessionCellModel(
        time = time,
        date = date,
        authorName = authorName,
        title = title
    )


fun Session.mapToSessionCellModel() =
    SessionCellModel(
        time = timeInterval,
        date = date,
        authorName = speaker,
        title = description
    )