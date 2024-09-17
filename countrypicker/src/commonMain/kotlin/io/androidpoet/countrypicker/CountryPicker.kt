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
import androidx.compose.ui.graphics.Color
import io.androidpoet.countrypicker.CountryUtils.getCurrentCountry
import io.androidpoet.countrypicker.CountryUtils.loadCountries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
public fun CountryPicker(
  onCountryChanged: (Country) -> Unit,
  onDismiss: () -> Unit,
  searchEnabled: Boolean = true,
  backgroundColor: Color = Color.White,
  searchBarColor: Color = Color.LightGray,
  isBottomSheetVisible: Boolean,
  itemContent: @Composable (Country, () -> Unit) -> Unit,
) {
  var state by rememberCountryPickerState()
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    coroutineScope.launch(Dispatchers.Default) {
      try {
        val loadedCountries = loadCountries(countriesJsonString)
        val currentCountry = getCurrentCountry(loadedCountries)
        state = state.copy(
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
    onDismiss = onDismiss,
    onItemClick = { country ->
      onDismiss()
      onCountryChanged(country)
    },
    searchEnabled = searchEnabled,
    searchBarBorderColor = searchBarColor,
    backgroundColor = backgroundColor,
    itemContent = itemContent,
    isBottomSheetVisible = isBottomSheetVisible,
  )
}
