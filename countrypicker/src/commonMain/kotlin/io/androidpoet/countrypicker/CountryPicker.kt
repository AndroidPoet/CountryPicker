/*
 * Designed and developed by 2024 androidpoet (Ranbir Singh)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.androidpoet.countrypicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.androidpoet.countrypicker.CountryUtils.getCurrentCountry
import io.androidpoet.countrypicker.CountryUtils.loadCountries
import kotlinx.coroutines.launch

// @Composable
// public fun CountryPicker(
//  onCountryChanged: (Country) -> Unit,
//  showBottomSheet: Boolean,
//  onDismiss: () -> Unit,
// ) {
//  var currentCountry by rememberSaveable(stateSaver = CountrySaver){ mutableStateOf<Country?>(null) }
//  var countries by rememberSaveable(stateSaver = CountrySaver) { mutableStateOf(emptyList<Country>()) }
//  LaunchedEffect(Unit) {
//    countries = loadCountries(countriesJsonString)
//    currentCountry = getCurrentCountry(countries)
//    currentCountry?.let { onCountryChanged(it) }
//  }
//
//  CountryListBottomSheet(
//    countries = countries,
//    showBottomSheet = showBottomSheet,
//    onDismiss = onDismiss,
//    onItemClick = {
//      currentCountry = it
//      onCountryChanged(it)
//    },
//  )
// }

@Composable
public fun CountryPicker(
  onCountryChanged: (Country) -> Unit,
  showBottomSheet: Boolean,
  onDismiss: () -> Unit,
) {
  var state by rememberCountryPickerState()
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    coroutineScope.launch {
      try {
        val loadedCountries = loadCountries(countriesJsonString)
        val currentCountry = getCurrentCountry(loadedCountries)
        state =
          state.copy(
            countries = loadedCountries,
            currentCountry = currentCountry,
          )
        currentCountry?.let { onCountryChanged(it) }
      } catch (e: Exception) {
      }
    }
  }

  CountryListBottomSheet(
    countries = state.countries,
    showBottomSheet = showBottomSheet,
    onDismiss = onDismiss,
    onItemClick = { country ->
      state = state.copy(currentCountry = country)
      onCountryChanged(country)
    },
  )
}