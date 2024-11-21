package baseNoStates;

/**
 * Class that represents a user with a name and a credential.
 */
public class User {
  private final String name;
  private final String credential;

  /**
   * Constructor for the User class
   * @param name Name of the user
   * @param credential Credential of the user
   */
  public User(String name, String credential) {
    this.name = name;
    this.credential = credential;
  }

  public String getCredential() {
    return credential;
  }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
