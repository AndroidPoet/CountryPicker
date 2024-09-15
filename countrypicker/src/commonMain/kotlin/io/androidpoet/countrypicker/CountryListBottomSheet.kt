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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CountryListBottomSheet(
  countries: List<Country>,
  showBottomSheet: Boolean,
  onDismiss: () -> Unit,
  onItemClick: (Country) -> Unit,
  searchEnabled: Boolean,
  itemBackgroundColor: Color,
  textColor: Color,
  searchBarBorderColor: Color
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val coroutineScope = rememberCoroutineScope()
  var isSheetVisible by remember { mutableStateOf(false) }
  var searchQuery by remember { mutableStateOf("") }
  val filteredCountries =
    remember(searchQuery, countries) {
      if (searchQuery.isEmpty()) {
        countries
      } else {
        countries.filter {
          it.name.contains(
            searchQuery,
            ignoreCase = true,
          ) ||
            it.phoneCountryCode.contains(searchQuery, ignoreCase = true)
        }
      }
    }

  LaunchedEffect(showBottomSheet) {
    if (showBottomSheet) {
      isSheetVisible = true
    } else {
      sheetState.hide()
    }
  }

  if (isSheetVisible) {
    ModalBottomSheet(
      modifier = Modifier.fillMaxHeight(0.9f),
      sheetState = sheetState,
      onDismissRequest = {
        coroutineScope.launch {
          sheetState.hide()
          isSheetVisible = false
          onDismiss()
        }
      },
      containerColor = Color.White,
    ) {
      Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        if (searchEnabled) {
          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text("Search countries") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            trailingIcon = {
              if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { searchQuery = "" }) {
                  Icon(Icons.Default.Clear, contentDescription = "Clear search")
                }
              }
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors =
            TextFieldDefaults.outlinedTextFieldColors(
              focusedBorderColor = searchBarBorderColor,
              unfocusedBorderColor = searchBarBorderColor,
            ),
          )
        }

        // Country list
        LazyColumn {
          items(filteredCountries) { country ->
            CountryItem(
              name = country.name,
              countryCode = country.phoneCountryCode,
              flag = country.flag.toString(),
              onItemClick = {
                coroutineScope.launch {
                  sheetState.hide()
                  isSheetVisible = false
                  onDismiss()
                  onItemClick(country)
                }
              },
              itemBackgroundColor = itemBackgroundColor,
              textColor = textColor
            )
          }
        }
      }
    }
  }
}
