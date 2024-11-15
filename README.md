# Weather App

A modern weather application built with Kotlin using MVVM (Model-View-ViewModel) architecture, Jetpack Compose for UI, and a local Room database for data persistence. This app fetches weather data from the [OpenWeather API](https://openweathermap.org/api) and stores relevant data locally for offline access.

## Features

- **Real-Time Weather Data**: Fetches up-to-date weather information for various locations.
- **Local Data Storage**: Uses Room Database to store weather data locally, ensuring offline access and improved user experience.
- **MVVM Architecture**: Follows a clean, modular approach for a maintainable codebase.
- **Jetpack Compose UI**: Leverages modern UI components for a smooth user experience.
- **Navigation**: Uses Jetpack Navigation for seamless transitions.
- **OpenWeather API Integration**: Fetches live weather data directly from OpenWeather's API.

## Architecture

The app follows the MVVM architecture pattern combined with a Repository and Room Database layer for clean data management. Here's an overview of the architecture:

- **Model**: Manages data sources and handles business logic.
- **View (UI)**: Built using Jetpack Compose, the UI is reactive and updates automatically based on data changes.
- **ViewModel**: Manages data to be displayed on the UI, making requests through the Repository.
- **Repository**: Acts as a single source of truth for data, handling data from both network and local sources.
- **Room Database**: Provides local data storage using SQLite with a DAO (Data Access Object) layer for efficient queries and data access.

### Room Database Overview

- **Entity**: Represents the data structure stored in the database.
- **DAO (Data Access Object)**: Defines methods to interact with the database, such as queries and data manipulation.
- **RoomDatabase**: The main database holder class that provides access to the DAO.


## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) for API calls
- **Local Storage**: [Room Database](https://developer.android.com/training/data-storage/room)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt)
- **Asynchronous Programming**: [Coroutines](https://developer.android.com/kotlin/coroutines)

## Setup and Configuration

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd weather-app
   ```

2. **Obtain an API Key**:
   - Visit [OpenWeather](https://openweathermap.org/api) and generate an API key.
   - Add the API key to the appropriate configuration file.

3. **Build and Run**:
   - Open the project in Android Studio.
   - Build and run the app on your preferred device or emulator.

## Usage

1. **View Current Weather**: Search for weather data of different cities.
2. **Weather Forecast**: Access detailed weather forecasts for the next few days.
3. **Offline Access**: View cached weather data when offline.
4. **Settings**: Customize user preferences as per needs.

## Dependencies

- **Retrofit**: API calls and networking.
- **Jetpack Compose**: UI components and layouts.
- **Room Database**: Local data storage.
- **Hilt**: Dependency injection.
- **Coroutines**: Asynchronous task handling.

## Contributing

Contributions are welcome! Feel free to improve features, fix bugs, or suggest new ideas:

1. Fork the repository.
2. Create a feature branch.
3. Commit your changes.
4. Open a pull request.

## License

```
Copyright 2013 Square, Inc.

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
