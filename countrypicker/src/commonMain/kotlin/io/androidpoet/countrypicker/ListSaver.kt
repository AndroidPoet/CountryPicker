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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable

public data class CountryPickerState(
  val countries: List<Country> = emptyList(),
  val currentCountry: Country? = null,
  val isLoading: Boolean = false,
  val error: String? = null,
)

public val CountryPickerStateSaver: Saver<CountryPickerState, Any> =
  listSaver(
    save = { state ->
      listOf(
        state.countries,
        state.currentCountry,
        state.isLoading,
        state.error,
      )
    },
    restore = { items ->
      CountryPickerState(
        countries = items[0] as List<Country>,
        currentCountry = items[1] as? Country,
        isLoading = items[2] as Boolean,
        error = items[3] as? String,
      )
    },
  )

@Composable
public fun rememberCountryPickerState(): MutableState<CountryPickerState> =
  rememberSaveable(stateSaver = CountryPickerStateSaver) {
    mutableStateOf(CountryPickerState())
  }
