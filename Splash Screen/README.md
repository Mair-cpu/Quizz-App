activity_splash_screen.xml (Splash Screen Layout)
ConstraintLayout: Centers and constrains UI elements (logo, text) vertically and horizontally for a balanced, responsive design.
ImageView: Displays the app logo with a specified size and content description for accessibility.
TextView: Renders styled text elements (app name, tagline, footer) with custom colors, sizes, and margins to create a branded intro screen.
Background Coloring: Applies a solid dark background color (#1E1E2C) to the root layout for a themed appearance.




SplashScreenActivity.java (Splash Screen Activity Class)
Handler with postDelayed: Implements a timed delay (2 seconds) to automatically transition to the next activity, simulating a loading or branding pause.
Intent Navigation: Uses Intent to start MainActivity and calls finish() to close the splash screen, preventing back navigation to it.
Status Bar Customization: Sets a custom status bar color (NavyBlue) and adjusts system UI visibility (e.g., light status bar icons) for Android M+ to match the app's theme.
Window Management: Accesses the activity's window to modify status bar appearance, enhancing the full-screen experience.
Activity Lifecycle: Initializes in onCreate with EdgeToEdge support (though not explicitly used here), focusing on setup and transition.
