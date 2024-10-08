package com.example.graphqlapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.graphqlapp.presentation.CountriesViewModel
import com.example.graphqlapp.presentation.CountryScreen
import com.example.graphqlapp.ui.theme.GraphQLAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GraphQLAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state = viewModel.state.collectAsState()
                CountryScreen(state = state.value,
                    onClick = viewModel::detailCountry,
                    dismiss = viewModel::dismissCountry
                    )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GraphQLAppTheme {
        Greeting("Android")
    }
}