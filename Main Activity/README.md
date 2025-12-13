activity_main.xml (Main Activity Layout)
DrawerLayout: Provides a sliding navigation drawer that can be pulled from the left side of the screen, allowing users to access menu options without leaving the main content.
ConstraintLayout: Used for flexible, responsive positioning of UI elements (e.g., toolbar, RecyclerView) with constraints, ensuring the layout adapts to different screen sizes.
Toolbar: A customizable action bar at the top, displaying the app title and menu icon for navigation.
ImageView: Displays the menu icon (e.g., a calendar view day icon) with tinting for visual consistency.
TextView: Shows the app title ("Quiz App") with styling like bold text and color.
RecyclerView: A scrollable list view for displaying quiz categories, optimized for performance with large datasets.
NavigationView: Integrates with the DrawerLayout to show a header and menu items, handling drawer-specific styling and interactions.




MainActivity.java (Main Activity Class)
DrawerLayout Management: Handles opening and closing the navigation drawer programmatically (e.g., via GravityCompat.START) in response to menu icon clicks.
NavigationView Listeners: Uses setNavigationItemSelectedListener to respond to menu item selections, triggering intents for sharing, rating, or viewing privacy policy.
Intent Handling: Creates and launches intents for external actions like sharing text via ACTION_SEND, opening Google Play for rating via ACTION_VIEW, or linking to a privacy policy URL.
View Binding: Finds and references UI elements (e.g., drawerLayout, navigationView) using findViewById for interaction.
Activity Lifecycle: Overrides onCreate to initialize the layout and set up event listeners, ensuring the drawer and menu work seamlessly.
