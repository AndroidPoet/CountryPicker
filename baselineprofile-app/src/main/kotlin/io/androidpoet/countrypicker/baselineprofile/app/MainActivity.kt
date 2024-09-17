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
package io.androidpoet.countrypicker.baselineprofile.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import io.androidpoet.countrypicker.CountryItem
import io.androidpoet.countrypicker.CountryPicker

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Box {
        var currantCountry by remember { mutableStateOf("") }
        var isBottomSheetVisible by remember { mutableStateOf(false) }
        CountryPicker(
          onCountryChanged = {
            currantCountry = it.name + " " + it.flag + " " + it.alpha2
          },
          onDismiss = {
            isBottomSheetVisible = false
          },
          itemContent = { country, onClick ->
            // pass your own layout here
            CountryItem(
              name = country.name,
              countryCode = country.phoneCountryCode,
              flag = country.flag.toString(),
              onItemClick = onClick,
              itemBackgroundColor = Color.White,
              textColor = Color.Black,
              currencyCode = country.currencyCode.orEmpty(),
              currencySign = country.currencySign.orEmpty(),
            )
          },
          isBottomSheetVisible = isBottomSheetVisible,
        )

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          Column {
            Text(currantCountry, fontSize = 20.sp)

            Button(onClick = {
              isBottomSheetVisible = !isBottomSheetVisible
            }) {
              Text("Open Country Picker", fontSize = 15.sp)
            }
          }
        }
      }
    }
  }
}
