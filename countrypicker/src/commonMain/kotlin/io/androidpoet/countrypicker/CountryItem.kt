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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CountryItem(
  name: String,
  countryCode: String,
  flag: String,
  onItemClick: () -> Unit,
) {
  Card(
    onClick = onItemClick,
    colors =
    CardColors(
      containerColor = Color.White,
      contentColor = Color.Transparent,
      disabledContainerColor = Color.Transparent,
      disabledContentColor = Color.Transparent,
    ),
  ) {
    Row(
      modifier =
      Modifier
        .fillMaxWidth()
        .clickable(onClick = onItemClick)
        .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = flag,
        fontSize = 24.sp,
        modifier = Modifier.padding(end = 16.dp),
        color = Color.Black,
      )

      Text(
        text = name,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.weight(1f),
        color = Color.Black,
      )
      Text(
        text = countryCode,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black,
      )
    }
  }
}
