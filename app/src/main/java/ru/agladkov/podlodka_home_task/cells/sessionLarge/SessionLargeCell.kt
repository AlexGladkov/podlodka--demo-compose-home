package ru.agladkov.podlodka_home_task.cells.sessionLarge

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import ru.agladkov.podlodka_home_task.R
import ru.agladkov.podlodka_home_task.cells.sessionSmall.SessionCell
import ru.agladkov.podlodka_home_task.cells.sessionSmall.SessionCellModel
import ru.agladkov.podlodka_home_task.ui.theme.PodlodkaComposeHomeTheme

@Composable
fun SessionLargeCell(
    model: SessionLargeCellModel,
    onFavoriteClick: (LargeSessionModel) -> Unit = { },
    onClick: (LargeSessionModel) -> Unit = { }
) {
    Column {
        Text(
            model.date,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
        Column {
            model.sessions.map {
                SessionLargeCellItem(
                    model = it,
                    onClick = { onClick.invoke(it) },
                    onFavoriteClick = { onFavoriteClick.invoke(it) }
                )
            }
        }
    }
}

@Composable
fun SessionLargeCellItem(
    model: LargeSessionModel,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit
) {
    val isFavorite = rememberSaveable { mutableStateOf(model.isFavorite) }

    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberCoilPainter(
                request = model.avatarUrl,
                fadeIn = true,
                requestBuilder = {
                    transformations(CircleCropTransformation())
                })

            Image(
                painter = painter,
                contentDescription = model.authorName,
                modifier = Modifier.size(64.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                Text(model.authorName)
                Text(model.time)
                Text("Доклад: ${model.title}", modifier = Modifier.padding(top = 8.dp))
            }

            Box(
                modifier = Modifier.size(56.dp)
                    .clickable {
                        isFavorite.value = !isFavorite.value
                        onFavoriteClick.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painterResource(
                        if (isFavorite.value)
                            R.drawable.ic_baseline_favorite_24
                        else
                            R.drawable.ic_baseline_favorite_border_24
                    ),
                    contentDescription = "Add to Favorite"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SessionLargeCellPreview() {
    PodlodkaComposeHomeTheme {
        SessionLargeCell(
            model = SessionLargeCellModel(
                "19 апреля",
                sessions = ArrayList<LargeSessionModel>().apply {
                    this += LargeSessionModel(
                        authorName = "Алексей Панов",
                        title = "Все что нужно знать о корутинах",
                        time = "10:00 - 11:00",
                        avatarUrl = "https://static.tildacdn.com/tild3432-3435-4561-b136-663134643162/photo_2021-04-16_18-.jpg",
                        isFavorite = false
                    )
                }
            )
        )
    }
}