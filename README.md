1. MainActivity
Summary: The entry point of the app that sets up the user interface using Jetpack Compose. It contains the navigation logic for moving between the CategoryListScreen and CategoryItemsScreen.
Use: Hosts the navigation logic and sets up the TopAppBar with a back button to navigate back or close the activity.

2. TodoListApp
Summary: The custom Application class annotated with @HiltAndroidApp, which sets up Hilt dependency injection for the app.
Use: Provides the base context for Hilt's dependency injection across the entire app.

3. CategoryViewModel
Summary: The ViewModel responsible for managing the UI-related data for categories in a lifecycle-conscious way. It handles fetching, adding, deleting, and updating categories.
Use: Provides the data and logic for the CategoryListScreen and is used to interact with the repository layer.

7. CategoryRepository
Summary: The repository that abstracts access to multiple data sources, specifically the local database for categories.
Use: Serves as the single source of truth for category data and provides methods to get, add, and delete categories.

4. CategoryListScreen
Summary: A Composable function that displays a list of categories and allows the user to add or delete categories.
Use: Handles the UI for the main screen, displaying all categories and offering buttons for adding or deleting categories.

5. CategoryItemsScreen
Summary: A Composable function that displays the items within a selected category, allowing the user to add, modify, delete, or mark items as done.
Use: Handles the UI for displaying and managing items within a selected category.

6. CategoryDao
Summary: The Data Access Object (DAO) interface for interacting with the Category table in the Room database.
Use: Provides methods for inserting, deleting, and querying categories in the local database.

7. Category
Summary: A data class representing a category in the app, containing properties like id and name.
Use: Serves as the model for categories, used across the app's ViewModel, Repository, and UI layers.

8. CategoryDatabase
Summary: The Room database class that contains the database holder and serves as the main access point for the underlying connection to the app's persisted data.
Use: Manages the database setup and provides DAOs to the app.

9. Hilt Modules (e.g., DatabaseModule)
Summary: Modules annotated with @Module and @InstallIn that provide dependencies for Hilt, such as the database instance and DAOs.
Use: Configures and provides the necessary dependencies for dependency injection throughout the app.

10. CategoryManagerTheme
Summary: A Composable function that applies the app's theme and styling.
Use: Ensures consistent styling across all UI components in the app.
