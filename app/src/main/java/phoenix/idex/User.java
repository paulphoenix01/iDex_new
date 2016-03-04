package phoenix.idex;

/**
 * Created by Ravinder on 3/2/16.
 */
public class User {
    private String firstname, lastname, email, username, password;

    // User for signing up
    public User(String firstname, String lastname, String email, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }
    // User for logging in
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
        this.firstname = "";
        this.lastname = "";
    }

    // Retrieve the data field of the class
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
