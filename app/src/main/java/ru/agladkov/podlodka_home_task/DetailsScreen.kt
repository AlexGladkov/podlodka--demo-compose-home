package ru.agladkov.podlodka_home_task

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.CoilImage
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.android.parcel.Parcelize
import ru.agladkov.podlodka_home_task.cells.sessionLarge.LargeSessionModel
import ru.agladkov.podlodka_home_task.ui.theme.PodlodkaComposeHomeTheme

@Parcelize
data class DetailsScreenModel(
    val datetime: String,
    val avatarUrl: String,
    val authorName: String,
    val title: String
): Parcelable

@Composable
fun DetailsScreen(model: DetailsScreenModel, navController: NavController) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp)) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberCoilPainter(
                        request = model.avatarUrl,
                        requestBuilder = {
                            transformations(CircleCropTransformation())
                        },
                        fadeIn = true
                    ),
                    alignment = Alignment.Center,
                    contentDescription = null,
                    modifier = Modifier.size(256.dp),
                )
            }

            Text(
                model.authorName, fontWeight = FontWeight.Medium, fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Row(modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)) {
                Icon(
                    painterResource(R.drawable.ic_baseline_calendar_today_24),
                    "Calendar"
                )

                Text(model.datetime, modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }

            Text("Доклад: ${model.title}")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    PodlodkaComposeHomeTheme {
        DetailsScreen(
            model = DetailsScreenModel(
                datetime = "26 апреля, 10:00 - 11:00",
                authorName = "Денис Неклюдов",
                title = "Камасутра с CameraX",
                avatarUrl = "https://static.tildacdn.com/tild3361-3637-4564-b939-346566383538/2020-06-11_115040.jpg"
            ), navController = rememberNavController()
        )
    }
}