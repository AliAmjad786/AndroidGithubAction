package com.example.graphqlapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry

@Composable
fun CountryScreen(
    state: CountriesViewModel.CountriesState,
    onClick: (code: String) -> Unit,
    dismiss: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(country = country) {
                        onClick(it)
                    }
                }
            }
        }

        if (state.selectedCountry != null) {
            CountryDetailScreen(state.selectedCountry, onDismiss = dismiss)
        }
    }
}

@Composable
fun CountryDetailScreen(
    country: DetailCountry,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = {onDismiss()}) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Row {
                Text(text = country.emoji)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = country.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "Continent:  ${country.continent}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Capital:  ${country.capital}")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Languages:  ${country.languages}")
        }
    }


}

@Composable
fun CountryItem(country: SimpleCountry, onClick: (code: String) -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(country.code) }
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = country.emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = country.name, fontSize = 16.sp)
                Text(text = country.code)
            }
        }
    }

}
