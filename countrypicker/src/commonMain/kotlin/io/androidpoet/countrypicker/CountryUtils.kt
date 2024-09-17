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

import androidx.compose.ui.text.intl.Locale
import de.cketti.codepoints.appendCodePoint
import kotlinx.serialization.json.Json

private const val REGIONAL_INDICATOR_OFFSET = 0x1F1A5

public object CountryUtils {
  private fun countryCodeToFlagEmoji(countryCode: String): String {
    require(countryCode.length == 2) { "Country code must be 2 characters long" }
    return buildString {
      for (char in countryCode.uppercase()) {
        appendCodePoint(char.code + REGIONAL_INDICATOR_OFFSET)
      }
    }
  }

  public fun generateCurrencySymbol(currencyCode: String): String {
    return when {
      currencyCode.length >= 2 -> {
        val firstChar = currencyCode[0].toUpperCase()
        val secondChar = currencyCode[1].toLowerCase()
        "$firstChar$secondChar"
      }

      currencyCode.length == 1 -> currencyCode.toUpperCase()
      else -> "?"
    }
  }

  internal fun loadCountries(jsonString: String): List<Country> = runCatching {
    Json.decodeFromString<List<Country>>(jsonString).map {
      it.copy(
        flag = countryCodeToFlagEmoji(it.alpha2),
      )
    }
  }.getOrElse { error ->
    println("Error parsing JSON ${error.message}")
    emptyList()
  }

  internal fun getCurrentCountry(countries: List<Country>): Country? =
    countries.find { it.alpha2 == Locale.current.region }
}
