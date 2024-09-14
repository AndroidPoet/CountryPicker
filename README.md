<h1 align="center">Compose Country Picker</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/yourusername/ComposePicker/actions/workflows/android.yml"><img alt="Build Status" 
  src="https://github.com/yourusername/ComposePicker/actions/workflows/android.yml/badge.svg"/></a>
</p>

<p align="center">
🌍 Compose Country Picker is a lightweight country picker library for Compose Multiplatform. It provides an easy-to-use and customizable component for selecting countries in your Compose Multiplatform applications.
</p>

## Features

- 🌐 Supports multiple platforms (Android, iOS, Desktop)
- 🎨 Customizable UI
- 🚀 Lightweight and efficient
- 🔍 Search functionality
- 🏳️ Includes country flags

## Download

Add the dependency below to your **module**'s `build.gradle` file:

```gradle
dependencies {
    implementation("io.github.androidpoet.countrypicker:0.1.1")
}
```

For Kotlin Multiplatform, add the dependency below to your **module**'s `build.gradle.kts` file:

```kotlin
sourceSets {
    val commonMain by getting {
        dependencies {
            implementation("io.github.androidpoet.countrypicker:0.1.1")
        }
    }
}
```

## Usage

Here's a basic example of how to use the ComposePicker in your Compose Multiplatform project:

```kotlin
@Composable
fun CountryPickerExample() {
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    
    ComposePicker(
        onCountrySelected = { country ->
            selectedCountry = country
        }
    )
    
    // Display selected country
    selectedCountry?.let {
        Text("Selected: ${it.name} (${it.code})")
    }
}
```

## Customization

ComposePicker offers various customization options:

```kotlin
ComposePicker(
    onCountrySelected = { country ->
        // Handle selection
    },
    searchEnabled = true,
    showFlags = true,
    itemBackgroundColor = Color.White,
    textColor = Color.Black,
    searchBarColor = Color.LightGray
)
```

## Contributing

Contributions are welcome! If you've found a bug, have an idea for an improvement, or want to contribute new features, please open an issue or submit a pull request.

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/androidpoet/countrypicker/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/androidpoet)__ on GitHub for my next creations! 🤩

## License
```
Designed and developed by AndroidPoet (Ranbir Singh)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```