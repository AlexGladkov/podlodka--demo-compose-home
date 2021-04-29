package ru.agladkov.podlodka_home_task.cells.sessionSmall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.agladkov.podlodka_home_task.ui.theme.PodlodkaComposeHomeTheme

@Composable
fun SessionCell(model: SessionCellModel, onClick: () -> Unit = { }) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.size(200.dp).padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(model.time)
            Text(model.date, modifier = Modifier.padding())
            Text(model.authorName)
            Text("Доклад: ${model.title}", maxLines = 3, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PodlodkaComposeHomeTheme {
        SessionCell(
            model = SessionCellModel(
                "10:00 - 11:00",
                "19 апреля",
                "Алексей Панов",
                "Все что нужно знать о корутинах",
            )
        )
    }
}