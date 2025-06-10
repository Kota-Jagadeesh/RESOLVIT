import javax.swing.*;

/**
 * The {@code MainApp} class serves as the entry point for the ResolveIt application.
 * It initializes and displays the main homepage GUI window.
 */
public class MainApp {

    /**
     * The main method of the application. This method launches the HomePage
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new HomePage().setVisible(true);
        });
    }
}
