# Weathering :partly_sunny:
![Logo](/app/src/main/ic_launcher-playstore.png)

#### A weather app but with beautiful artwork. These artworks are made by me with suggestions from various people to make it look perfect.

> Goal is to keep it minimal!

## Features

- Fetching weather data from a remote API using Retrofit and Kotlin Coroutines.
- Made using kotlin and following business logic MVVM to setup everything in the project.
- Current location fetched using google API

## Screenshots
<img src = "/assets/screenshots/1.jpeg" alt="screenshot1" width="200"/> <img src = "/assets/screenshots/2.jpeg" alt="screenshot2" width="200"/> <img src = "/assets/screenshots/3.jpeg" alt="screenshot3" width="200"/> <img src = "/assets/screenshots/4.jpeg" alt="screenshot4" width="200"/> <img src = "/assets/screenshots/5.jpeg" alt="screenshot5" width="200"/> <img src = "/assets/screenshots/6.jpeg" alt="screenshot6" width="200"/>


## Libraries Used

- Retrofit: For making network requests and fetching weather data from the API.
- RecyclerView: For displaying a list of libraries.
- Hilt: For dependency injection
- Room db: For storing the fetched weather data from openWeatherApi 
- Google Api: To fetch the current location of the user
- LeakCanary: To show if there is any memory leak during the debug mode.
## Getting Started

To run the Weathering App on your local machine, follow these steps:

1. Clone the repository: `git clone https://github.com/FireLord/Weathering.git`
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

Make sure you have an active internet connection to fetch the latest news data from the API.