package graph;

import constant.IConstant;
import model.User;
import java.util.*;
import static mock.Data.socialNetwork;
import util.Validate;

public class SocialNetwork {

  private SocialGraph graph;

  public SocialNetwork() {
    this.graph = new SocialGraph();
  }

  /**
   * Adds a user to the social network.
   *
   * @param user the user to add.
   */
  public void addUser(User user) {
    graph.addUser(user);
  }

  /**
   * Adds a friendship between two users.
   *
   * @param user1 the first user.
   * @param user2 the second user.
   */
  public void addFriendship(User user1, User user2) {
    graph.addFriendship(user1, user2);
  }

  /**
   * Gets the friends of a user.
   *
   * @param user the user whose friends to retrieve.
   * @return a list of the user's friends.
   */
  public List<User> getFriends(User user) {
    return graph.getFriends(user);
  }

  /**
   * Recommends friends for a given user.
   *
   * @param user               the user to recommend friends for.
   * @param maxRecommendations the maximum number of recommendations to return.
   * @return a list of recommended users, sorted by relevance.
   */
  public List<User> recommendFriends(User user, int maxRecommendations) {
    List<User> friends = graph.getFriends(user);
    List<User> allUsers = graph.getAllUsers();
    Map<User, Integer> recommendations = new HashMap<>();

    for (User potentialFriend : allUsers) {
      if (!potentialFriend.equals(user) && !friends.contains(potentialFriend)) {
        int mutualFriends = countMutualFriends(user, potentialFriend);
        if (mutualFriends > 0) {
          recommendations.put(potentialFriend, mutualFriends);
        }
      }
    }

    List<Map.Entry<User, Integer>> sortedRecommendations;
    sortedRecommendations = new ArrayList<>(recommendations.entrySet());
    sortedRecommendations.sort((a, b) -> b.getValue().compareTo(a.getValue()));

    List<User> topRecommendations = new ArrayList<>();
    for (int i = 0; i < Math.min(maxRecommendations, sortedRecommendations.size()); i++) {
      topRecommendations.add(sortedRecommendations.get(i).getKey());
    }

    return topRecommendations;
  }

  /**
   * Counts the number of mutual friends between two users.
   *
   * @param user1 the first user.
   * @param user2 the second user.
   * @return the number of mutual friends.
   */
  private int countMutualFriends(User user1, User user2) {
    List<User> friends1 = graph.getFriends(user1);
    List<User> friends2 = graph.getFriends(user2);
    int count = 0;
    for (User friend : friends1) {
      if (friends2.contains(friend)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Gets all users in the social network.
   *
   * @return a list of all users.
   */
  public List<User> getAllUsers() {
    return graph.getAllUsers();
  }
  
  
  // add người dùng mới
    public void addNewUser(User user) {
        Scanner sc = new Scanner(System.in);
        List<User> allUsers = graph.getAllUsers();
        
        int cnt=0;
        for (int i = 0; i < allUsers.size(); i++) {
            if(user == allUsers.get(i)){
                System.out.println("User already exists ");
                cnt++;
                return;
            }
        }
        if(cnt==0){
            addUser(user);
            System.out.println("Enter the number of friendship: ");
            int fs= sc.nextInt();
            
            while(fs!=0){
                System.out.println("Enter name of his/her friend: ");
                 User user2 = new User(sc.nextLine());
                addFriendship(user, user2);
                fs--;
            }
        }
        
        System.out.println("Add complete!!!" );
        
  }
    
    
    public static void removeUser() {
        String username="";
        username = Validate.getString(
                "Enter username: ",
                "Username is invalidated!",
                IConstant.REGEX_USER_NAME
        );
        List<User> allUsers = socialNetwork.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            
            if(allUsers.get(i).getUsername().equalsIgnoreCase(username)){
                System.out.println(allUsers.get(i));
                
                allUsers.remove(allUsers.get(i));
               
            }
        }
        
        
        
    }
    //-------------------------------------
}
