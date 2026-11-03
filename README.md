# to-do-list

A small Android to-do list application built with Kotlin and Jetpack Compose. Tasks are stored locally with Room and are exposed to the Compose UI through a `TodoViewModel`.

## Features

- View tasks stored in the local Room database.
- Add tasks to and delete them from the Compose screen.
- Keep task data on the device between app launches.
- Distinguish between open and completed tasks.
- Edit tasks.

> The project is an prototype.

## Requirements

- Android Studio with Android SDK 36 installed.
- JDK 11 or a compatible Android Studio-managed JDK.
- An Android device or emulator running Android 7.0 (API 24) or newer.

## Getting Started

1. Clone the repository and open the project in Android Studio.
2. Allow Android Studio to sync the Gradle project and install any requested SDK components.
3. Select the `app` run configuration.
4. Start an emulator or connect an Android device.
5. Click **Run** in Android Studio.

The application package is `com.example.to_do_list`. The app creates a local Room database named `app_database` on first launch.

## Build From the Command Line

On Windows:

```powershell
.\gradlew.bat assembleDebug
```

On macOS or Linux:

```bash
./gradlew assembleDebug
```

The debug APK is generated under `app/build/outputs/apk/debug/`.

## Test

Run local unit tests with:

```powershell
.\gradlew.bat test
```

Run the Android instrumented tests on a connected device or emulator with:

```powershell
.\gradlew.bat connectedAndroidTest
```

## Project Structure

```text
app/src/main/java/com/example/to_do_list/
├── data/       Room entity, DAO, database, and repository code
├── ui/         Compose screens and theme
├── viewmodel/  UI state and task actions
└── MainActivity.kt
```

The main flow is `MainActivity` -> `TodoViewModel` -> `TodoDao` -> Room. `TodoScreen` observes the task flow and sends new task titles to the view model.


## Support

For project-specific questions or bug reports, open a GitHub issue in this repository with reproduction steps, device/API details, and relevant build output. Android and library documentation is available from the [Android Developers documentation](https://developer.android.com/docs).


## Maintainer

This project is maintained by Angela Pense (https://github.com/angelapense99).

## License

This project is licensed under the MIT License.
