package bo;

import graph.SocialNetwork;
import model.User;
import java.util.List;

public class UserBo {

  private SocialNetwork socialNetwork;

  /**
   * Constructs a new UserBo with the given SocialNetwork.
   *
   * @param socialNetwork the SocialNetwork to use for operations.
   */
  public UserBo(SocialNetwork socialNetwork) {
    this.socialNetwork = socialNetwork;
  }

  /**
   * Gets friend recommendations for a given user.
   *
   * @param username the username to get recommendations for.
   * @param maxRecommendations the maximum number of recommendations to return.
   * @return a list of recommended users, or null if the user doesn't exist.
   */
  public List<User> getFriendRecommendations(String username, int maxRecommendations) {
    User user = findUserByUsername(username);
    if (user == null) {
      return null;
    }
    return socialNetwork.recommendFriends(user, maxRecommendations);
  }

  /**
   * Gets the friend list for a given user.
   *
   * @param username the username to get the friend list for.
   * @return a list of the user's friends, or null if the user doesn't exist.
   */
  public List<User> getFriendList(String username) {
    User user = findUserByUsername(username);
    if (user == null) {
      return null;
    }
    return socialNetwork.getFriends(user);
  }

  /**
   * Checks if a user exists in the social network.
   *
   * @param username the username to check.
   * @return true if the user exists, false otherwise.
   */
  public boolean isExist(String username) {
    return findUserByUsername(username) != null;
  }

  /**
   * Finds a user by their username.
   *
   * @param username the username to search for.
   * @return the User object if found, null otherwise.
   */
  private User findUserByUsername(String username) {
    for (User user : socialNetwork.getAllUsers()) {
      if (user.getUsername().equalsIgnoreCase(username)) {
        return user;
      }
    }
    return null;
  }
  
  
  public void addNEW(String username){
      User user = new User(username);
      socialNetwork.addNewUser(user);
  }
}
