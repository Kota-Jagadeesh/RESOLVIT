/**
 * User class represents a user entity in the complaint management system.
 * It encapsulates details such as user ID, name, and role (student or admin).
 * The class provides getter methods for accessing the attributes.
 */
public class User {
    // Instance variables for user attributes
    private String userId; // Unique identifier for the user
    private String name;   // Name of the user
    private String role;   // Role of the user, either "student" or "admin"

    /**
     * Constructor for User.
     * Initializes a User object with the specified user ID, name, and role.
     * @param userId The unique identifier for the user
     * @param name   The name of the user
     * @param role   The role of the user ("student" or "admin")
     */
    public User(String userId, String name, String role) {
        // Assign the provided values to instance variables
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

    /**
     * Retrieves the user ID.
     *
     * @return The unique identifier of the user
     */
    public String getUserId() { 
        return userId; 
    }

    /**
     * Retrieves the user's name.
     *
     * @return The name of the user
     */
    public String getName() { 
        return name; 
    }

    /**
     * Retrieves the user's role.
     *
     * @return The role of the user ("student" or "admin")
     */
    public String getRole() { 
        return role; 
    }
}