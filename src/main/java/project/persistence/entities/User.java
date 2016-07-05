package project.persistence.entities;

import javax.persistence.*;

/**
 * The class for the Question itself.
 * The system generates a table schema based on this class for this entity.
 * Be sure to annotate any entities you have with the @Entity annotation.
 */
@Entity
@Table(name = "users") // If you want to specify a table name, you can do so here
public class User {

    // Declare that this attribute is the id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String userLocation;
    private String userName;
    private String countryRaisedIn;
    private String lastLogin;
    private String dateCreated;
    private String age;
    private String gender;
    private String language;

    // Notice the empty constructor, because we need to be able to create an empty Survey to add
    // to our model so we can use it with our form
    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUserLocation() { return userLocation; }

    public void setUserLocation(String userLocation) { this.userLocation = userLocation; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getCountryRaisedIn() { return countryRaisedIn; }

    public void setCountryRaisedIn(String countryRaisedIn) { this.countryRaisedIn = countryRaisedIn; }

    public String getLastLogin() { return lastLogin; }

    public void setLastLogin(String lastLogin) { this.lastLogin = lastLogin; }

    public String getDateCreated() { return dateCreated; }

    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }

    public String getAge() { return age; }

    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public String getLanguage() { return language; }

    public void setLanguage(String language) { this.language = language; }

    // This is for easier debug.
    @Override
    public String toString() {
        return String.format(
                "User [firstName=%s, lastName=%s, countryRaisedIn=%s]",
                firstName, lastName, countryRaisedIn);
    }
}
