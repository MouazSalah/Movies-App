# Movies-App

This project is a movie listing application developed as part of the Paymob Mobile Technical Task. The application allows users to browse a list of the best movies of 2024, view detailed information about each movie, and add or remove movies from their favorites list.

## Features

- **Movie List Screen:**
  - Displays a list of movies fetched from TheMovieDB API.
  - Each movie entry shows the movie poster, name, rating, release date, and an option to add/remove the movie from favorites.

- **Movie Details Screen:**
  - Displays detailed information about the selected movie, including an overview, vote average, original language, and more.
  - Users can favorite or unfavorite a movie, and this status is saved locally.

## Technologies Used

### Android (Kotlin)
- **Language:** Kotlin
- **Architecture:** MVVM (Model-View-ViewModel)
- **UI:** XML Layouts
- **Networking:** Retrofit
- **Image Loading:** Glide
- **Local Storage:** Room Database
- **Dependency Injection:** Hilt

### iOS (Swift)
- **Language:** Swift
- **Architecture:** MVVM
- **UI:** UIKit (Storyboard/XIB)
- **Networking:** URLSession
- **Image Loading:** SDWebImage
- **Local Storage:** Core Data
- **Dependency Injection:** Swift Dependency Injection (if applicable)

## Project Structure

The project is structured using a modular approach, organized by features and layers for better maintainability and scalability.

### Core Modules

- **`core/`**: Contains core functionality that is shared across different modules, such as base application classes, dependency injection setup, and utility functions.

  - **BaseApp**: The base application class for initializing core configurations.
  - **DIAnnotation**: Annotations used for dependency injection.
  - **HashMapParams**: A utility class for handling parameters in a HashMap format.

### Data Layer

- **`data/`**: Handles data fetching from the API, local storage, and manages the repository layer for the details and listing modules.

  - **API**: Contains all API-related classes and interfaces, such as Retrofit service definitions.
  - **Models**: Data models used to parse and map API responses.
  - **Params**: Classes that define the parameters used in API calls and repository queries.
  - **Repository**: Implements the repository pattern to provide a clean API for data access to the rest of the app.
  - **Datastore**: Manages data persistence using Room for local storage and Retrofit for remote calls.

### Domain Layer

- **`domain/`**: Contains the business logic, including entities, mappers, and use cases for both movie details and listing.

  - **Entities**: Business objects used throughout the application.
  - **Mapper**: Classes responsible for converting data models to domain entities and vice versa.
  - **Repository Interface**: Abstraction layer for data access, implemented by the repository classes in the data layer.
  - **Use Cases**: Application-specific business rules, which orchestrate the flow of data from the repository to the UI.

### Presentation Layer

- **`presentation/`**: Contains all UI-related components, including fragments, view models, states, and adapters.

  - **Fragments**: UI components tied to a particular screen (e.g., MovieDetailsFragment, MoviesFragment).
  - **ViewModels**: Handle UI-related data and business logic, interacting with the use cases.
  - **Adapters**: Bind data to views, particularly for RecyclerViews (e.g., MoviesAdapter).
  - **UI State**: Represents the state of the UI, ensuring that the UI updates reactively to data changes (e.g., MovieDetailsState, MoviesListingState).

### DI (Dependency Injection)

- **`di/`**: Manages dependency injection setup, ensuring that the correct dependencies are provided to various parts of the application using Hilt for Android and Swift DI for iOS.

### Extension Module

- **`extesnion/`**: Houses Kotlin extension functions to enhance and simplify code throughout the application.

### Modules

- **`modules/`**: The application is divided into modular features, such as `details`, `listing`, and `common_views` for shared UI components.

  - **Details Module**: Handles everything related to displaying and managing movie details.
  - **Listing Module**: Manages the movie list screen, including fetching, displaying, and managing the list of movies.
  - **Common Views**: Contains shared UI components, such as custom views that can be reused across different modules.

### Splash Module

- **`splash/`**: Manages the splash screen functionality, which is the entry point of the application.
