# 🎬 Characters App (Anime Edition)

A modern Android application built with **MVVM Clean Architecture** that provides a seamless anime browsing experience with offline-first capabilities.

## 📱 Features Implemented

### ✅ Core Features
- **Anime Discovery**: Browse top anime with detailed information (ratings, episodes, synopsis, genres)
- **Offline-First**: Works seamlessly without internet using intelligent caching
- **Real-time Search**: Filter anime by name with instant results
- **Favorites Management**: Save and manage favorite anime in local database
- **Detailed Views**: Comprehensive anime details with trailers and cast information

### ✅ Technical Features
- **Modern Android Development**: Jetpack Compose, Kotlin, Coroutines
- **Dependency Injection**: Hilt for clean architecture
- **Local Caching**: Room database with smart cache invalidation
- **Navigation**: Jetpack Navigation with bottom navigation
- **Image Loading**: Coil for efficient image handling
- **API Integration**: Retrofit with OkHttp for network calls
- **Shared Components**: Centralized `AppText` component for consistent typography

### 📺  Demo Video : For convenience, the APK has been uploaded to the repository.
<br>

## 🏗️ Architecture

### Folder Structure Diagram

```
📁 app/src/main/java/com/example/characters/
├── 📁 common/                      # Shared Constants & Utilities
│   ├── Constants.kt                # App-wide Constants
│   └── Resource.kt                 # Resource Management
├── 📁 data/                        # Data Layer
│   ├── 📁 local/                   # Local Database Layer
│   │   ├── CharacterDB.kt          # Room Database Configuration
│   │   └── CharacterDao.kt         # Data Access Objects
│   ├── 📁 remote/                  # Remote API Layer
│   │   ├── CharacterApi.kt         # Retrofit API Interface
│   │   └── 📁 dto/                 # Data Transfer Objects
│   │       ├── AnimeDTO.kt         # API Response Models
│   │       └── AnimeDetailDTO.kt   # Detailed Anime Models
│   └── 📁 repository/              # Repository Implementations
│       ├── CharacterRepoImpl.kt    # Anime Repository Implementation
│       └── DbRepoImpl.kt           # Database Repository Implementation
├── 📁 di/                          # Dependency Injection
│   ├── DataModule.kt               # Data Layer Dependencies
│   ├── DatabaseModule.kt           # Database Dependencies
│   ├── NetworkModule.kt            # Network Dependencies
│   └── UseCaseModule.kt            # Use Case Dependencies
├── 📁 domain/                      # Business Logic Layer
│   ├── 📁 model/                   # Domain Models
│   │   └── CharacterDisplay.kt     # UI Models & Entities
│   ├── 📁 repository/              # Repository Interfaces
│   │   ├── CharacterRepository.kt  # Anime Repository Contract
│   │   └── DbRepository.kt         # Database Repository Contract
│   └── 📁 usecases/                # Business Use Cases
│       ├── AllUseCases.kt          # Use Case Collection
│       ├── FetchCharacters.kt      # Fetch Characters Logic
│       ├── GetAnimeDetailsUseCase.kt # Anime Details Logic
│       ├── GetCharactersUseCase.kt # Main Anime Fetching Logic
│       ├── RemoveFavorites.kt      # Remove Favorites Logic
│       └── SaveCharacter.kt        # Save Character Logic
├── 📁 navigation/                  # Navigation Layer
│   ├── AppNavHost.kt               # Navigation Graph
│   ├── AppRoot.kt                  # Main App Composable
│   ├── Navigation.kt               # Navigation Components
│   └── Screen.kt                   # Screen Definitions
├── 📁 presentation/                # UI Layer (MVVM Views)
│   ├── MainActivity.kt             # App Entry Point
│   ├── 📁 character_detail/        # Anime Detail Screen
│   │   ├── AnimeDetailState.kt     # Detail View State
│   │   ├── AnimeDetailViewModel.kt # Detail ViewModel
│   │   ├── CharacterDetailScreen.kt # Detail Screen UI
│   │   └── 📁 components/          # Shared UI Components
│   │       ├── AppText.kt          # Centralized Text Component
│   │       ├── MediaSection.kt     # Media Display Component
│   │       ├── NoMediaPlaceholder.kt # No Media Placeholder
│   │       ├── PosterImage.kt      # Poster Image Component
│   │       └── TrailerView.kt      # Trailer Video Component
│   ├── 📁 character_list/          # Anime List Screen
│   │   ├── CharacterListScreen.kt  # List Screen UI
│   │   ├── CharacterListState.kt   # List View State
│   │   ├── CharacterListViewModel.kt # List ViewModel
│   │   └── 📁 components/          # Reusable UI Components
│   │       ├── CharacterListItem.kt # List Item Component
│   │       ├── SearchComponent.kt  # Search Bar Component
│   │       └── SearchNotFoundUi.kt # Search Not Found UI
│   ├── 📁 favorites/               # Favorites Screen
│   │   ├── FavoritesScreen.kt      # Favorites UI
│   │   ├── FavoritesState.kt       # Favorites State
│   │   ├── FavoritesViewModel.kt   # Favorites ViewModel
│   │   └── 📁 components/          # Favorites Components
│   │       ├── FavCharacterItem.kt # Favorite Character Item
│   │       └── NoFavoritesUi.kt    # No Favorites UI Component
│   └── 📁 ui/                      # UI Theme & Design
│       ├── Color.kt                # Color Palette
│       ├── Theme.kt                # App Theme
│       ├── Type.kt                 # Typography Definitions
│       └── Shapes.kt               # Shape Definitions
└── 📄 CharacterApplication.kt      # Hilt Application Class
```

### Architecture Principles

- **MVVM Pattern**: ViewModels handle UI state, Views are pure composables
- **Clean Architecture**: Clear separation of concerns with data flow
- **Dependency Injection**: Hilt manages object creation and injection
- **Repository Pattern**: Single source of truth for data operations
- **Offline-First**: Cache-first strategy with background sync
- **Component Architecture**: Shared, reusable UI components with centralized styling

## 📋 Assumptions Made

### Technical Assumptions
- **Target API**: Android API 24+ (minSdk 24)
- **Network**: Jikan API (MyAnimeList API) availability
- **Device**: Modern Android devices with Compose support
- **Storage**: Adequate local storage for caching


### ✅ Recent Improvements
- **Centralized Text Component**: `AppText` component with `TextType` enum for consistent typography across the app
- **Component Architecture**: Shared reusable components in dedicated component folders
- **Code Quality**: Refactored screens to follow standard best practices.

### Future Improvements
- **Pagination**: Currently loads single page of results
- **Advanced Search**: Not Limited to title-based search
- **User Authentication**
- **Dark/Light Mode**


## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Arctic Fox or later
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)
- **Kotlin**: 1.8.10+

### Installation
1. Clone the repository
2. Open in Android Studio
3. Sync project with Gradle files
4. Run on device/emulator