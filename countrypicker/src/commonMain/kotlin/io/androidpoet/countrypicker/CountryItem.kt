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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun CountryItem(
  name: String,
  countryCode: String,
  flag: String,
  currencyCode: String,
  currencySign: String,
  itemBackgroundColor: Color,
  textColor: Color,
  onItemClick: () -> Unit,
) {
  Card(
    onClick = onItemClick,
    colors = CardDefaults.cardColors(
      containerColor = itemBackgroundColor,
      contentColor = Color.Transparent,
    ),
    modifier = Modifier.fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 12.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = flag,
        fontSize = 24.sp,
        modifier = Modifier.padding(end = 16.dp),
        color = textColor,
      )

      Column(
        modifier = Modifier.weight(1f),
      ) {
        Text(
          text = name,
          style = MaterialTheme.typography.bodyLarge,
          color = textColor,
        )
        Text(
          text = "$currencyCode • $currencySign",
          style = MaterialTheme.typography.bodySmall,
          color = textColor.copy(alpha = 0.7f),
        )
      }

      Text(
        text = countryCode,
        style = MaterialTheme.typography.bodyMedium,
        color = textColor,
      )
    }
  }
}
