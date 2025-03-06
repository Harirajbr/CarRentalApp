# Car Rental App

This is a simple Car Rental Android application that allows users to search for rental cars.

## Features

### Car Rental Search:
- Enter pickup and drop-off locations.
- Select pickup and drop-off dates.
- Search for available rental cars.

### Kayak Integration:
- Redirects users to Kayak's car rental page with pre-filled search parameters.

## Installation

### Clone the repository:
```bash
git clone <https://github.com/Harirajbr/CarRentalApp>
```

1. Open the project in Android Studio.
2. Build and run the app on an emulator or physical device.

## Dependencies
- AndroidX libraries (AppCompat, Material)
- Compose UI
- (Potentially) Google Places API

## Project Structure
```
app/src/main/java/com/example/carrental/        # Contains main activities (MainActivity.kt, SplashActivity.kt)
app/src/main/java/com/example/carrental/ui/theme/ # Contains the Compose UI theme
app/src/main/res/layout/                        # Contains layout files for activities
app/src/main/res/values/                        # Contains resources like strings, colors, and styles
```

## Usage
1. Launch the app.
2. Enter pickup and drop-off locations.
3. Select pickup and drop-off dates.
4. Tap the "SEARCH" button to be redirected to Kayak.
