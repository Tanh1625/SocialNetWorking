package model;

public class User {

  private  String username;

  public User(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public String toString() {
    return ": "+ username ;
  }

  //Checks if this User is equal to another User.

  public boolean equals(User other) {
    if (other == null) {
      return false;
    }
    return this.username.equals(other.username);
  }

    public void setUsername(String username) {
        this.username = username;
    }
  
}
