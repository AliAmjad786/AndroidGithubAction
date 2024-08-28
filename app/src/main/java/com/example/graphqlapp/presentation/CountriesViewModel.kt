package com.example.graphqlapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry
import com.example.graphqlapp.domain.usecases.GetCountriesUseCase
import com.example.graphqlapp.domain.usecases.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        execute()
    }

    private fun execute() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }

        }
    }

    fun detailCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }
    fun dismissCountry() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = null
                )
            }
        }
    }


    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailCountry? = null
    )
}