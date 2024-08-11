About the Category Management Application

Features
1. Category Management:
   - List of categories (e.g., Shopping, Work, Misc).
   - Add new categories.
   - Edit existing categories.
   - Delete categories.

2. Todo Item Management:
   - View list of todo items associated with each category.
   - Add new todo items.
   - Edit existing todo items.
   - Delete todo items.
   - Mark todo items as Pending/Done.

3. Data Persistence:
   - Store categories and todo items using a local database (Room).

4. Search Functionality:
   - Search categories and todo items.

5. UI/UX:
   - Use Jetpack Compose for a modern and responsive UI.
   - Floating Action Buttons for adding categories and todo items.

---

 Tech Stack

1. Kotlin: Programming language for Android development.
2. Jetpack Compose: UI toolkit for building native Android UIs.
3. ViewModel: Architecture component for managing UI-related data lifecycle-consciously.
4. Room: Local database for storing data.
5. Hilt: Dependency injection library for Android.
6. Espresso: UI testing framework.
7. JUnit: Unit testing framework.
8. Navigation Component: For handling navigation between screens.

---

 Installation

 Prerequisites
- Android Studio (latest stable version)
- Kotlin (version compatible with your Android Studio)

 Steps

1. Clone the Repository:
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   ```

2. Navigate to Project Directory:
   ```bash
   cd your-repo-name
   ```

3. Open Project in Android Studio:
   - Launch Android Studio.
   - Select File > Open and choose the project directory.

4. Sync Gradle:
   - Android Studio will prompt you to sync Gradle. Click Sync Now.

5. Build the Project:
   - Click on Build > Rebuild Project to ensure everything is set up correctly.

6. Run the App:
   - Connect an Android device or start an emulator.
   - Click Run (green play button) to build and run the app on your device or emulator.

---

 Usage

 Starting the App
- Open the app to see the Category List Screen.
- From the Category List Screen, you can:
  - Add a new category using the Floating Action Button (FAB).
  - Select a category to view its associated todo items.
  - Use the search functionality to find specific categories.

 Managing Categories
- Click the Add button to create a new category.
- Click on a category to view and manage todo items.
- Click the Delete icon next to a category to remove it.

 Managing Todo Items
- While viewing a category, click the Add button to create a new todo item.
- Use the list to mark items as Pending/Done.
- Click the Delete icon next to a todo item to remove it.

---

 Code Structure
com.example.categorymanager
├── data
│   ├── db
│   │   ├── AppDatabase.kt
│   │   ├── CategoryDao.kt
│   │   ├── CategoryEntity.kt
│   │   ├── ItemDao.kt
│   │   ├── ItemEntity.kt
├── di
│   ├── AppModule.kt
├── ui
│   ├── MainActivity.kt
│   ├── CategoryListScreen.kt
│   ├── CategoryItemsScreen.kt
├── viewmodel
│   ├── CategoryViewModel.kt
│   ├── ItemViewModel.kt
└── CategorymanagerApp.kt

 Project Directory
- `app/`: Contains the main application code.
  - `src/main/java/com/example/todolistapp/`:
    - `model/`: Data models (e.g., `Category.kt`, `TodoItem.kt`).
    - `repository/`: Data repository classes for accessing data.
    - `ui/`:
     - `screen/`: Composables for different screens (e.g., `CategoryListScreen.kt`, `TodoListScreen.kt`).
    - `viewmodel/`: ViewModel classes (e.g., `CategoryViewModel.kt`, `TodoItemViewModel.kt`).
    - `navigation/`: Navigation setup and configurations.
  - `src/main/res/`: Resources such as layouts, strings, and themes.
  - `src/main/AndroidManifest.xml`: AndroidManifest file with app configurations.

 Key Files
- `MainActivity.kt`: Entry point of the application. Sets up the navigation and displays the initial screen.
- `CategoryViewModel.kt`: Manages category-related data and interactions.
- `TodoItemViewModel.kt`: Manages todo item-related data and interactions.
- `CategoryListScreen.kt`: Composable for displaying and managing categories.
- `TodoListScreen.kt`: Composable for displaying and managing todo items within a category.
- `TodoDatabase.kt`: Defines the Room database and DAOs.

 Build Configuration
- `build.gradle.kts`: Defines dependencies and build settings.
- `libs.versions.toml`: Manages dependency versions for the project.
