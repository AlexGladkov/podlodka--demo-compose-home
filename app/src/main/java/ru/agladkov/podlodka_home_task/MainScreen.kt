package ru.agladkov.podlodka_home_task

import HeaderText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import ru.agladkov.podlodka_home_task.cells.sessionLarge.SessionLargeCell
import ru.agladkov.podlodka_home_task.cells.sessionLarge.SessionLargeCellModel
import ru.agladkov.podlodka_home_task.cells.sessionSmall.SessionCell
import ru.agladkov.podlodka_home_task.cells.sessionSmall.SessionCellModel
import ru.agladkov.podlodka_home_task.navigation.NavCommand
import ru.agladkov.podlodka_home_task.ui.theme.PodlodkaComposeHomeTheme

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = MainViewModel(),
) {
    val viewState = viewModel.state.observeAsState(MainViewState())

    LaunchedEffect(viewModel.needToReload) {
        viewModel.parseMockData()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        val searchState = remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            onValueChange = { searchState.value = it },
            value = searchState.value
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (viewState.value.favorites.isNotEmpty()) {
                item { HeaderText(text = "Избранное", modifier = Modifier.padding(16.dp)) }
                item {
                    SessionsRow(
                        data = viewState.value.favorites,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            viewState.value.sessionsList.map { sessionLargeModel ->
                item {
                    SessionLargeCell(
                        model = sessionLargeModel,
                        onFavoriteClick = {
                            viewModel.addToFavorite(model = it, date = sessionLargeModel.date)
                        },
                        onClick = {
                            navController
                                .currentBackStackEntry
                                ?.arguments
                                ?.putParcelable(
                                    "model", DetailsScreenModel(
                                        authorName = it.authorName,
                                        avatarUrl = it.avatarUrl,
                                        datetime = "${sessionLargeModel.date} ${it.time}",
                                        title = it.title
                                    )
                                )
                            navController.navigate(
                                NavCommand.Details.title
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SessionsRow(data: List<SessionCellModel>, modifier: Modifier = Modifier) {
    LazyRow(modifier = Modifier) {
        data.map { item { SessionCell(it) } }
    }
}