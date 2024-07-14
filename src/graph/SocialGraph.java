package graph;

import model.User;
import java.util.*;

public class SocialGraph {

  private Map<String, SocialVertex> vertex;

  public SocialGraph() {
    this.vertex = new HashMap<>();
  }

  /**
   * Adds a user to the social graph.
   *
   * @param user the user to add.
   */
  public void addUser(User user) {
    vertex.putIfAbsent(user.getUsername(), new SocialVertex(user));
  }

  /**
   * Adds a friendship between two users.
   *
   * @param user1 the first user.
   * @param user2 the second user.
   */
  public void addFriendship(User user1, User user2) {
    SocialVertex n1 = vertex.get(user1.getUsername());
    SocialVertex n2 = vertex.get(user2.getUsername());
    if (n1 != null && n2 != null) {
      n1.addFriend(n2);
      n2.addFriend(n1);
    }
  }

 
  public List<User> getFriends(User user) {
    SocialVertex node = vertex.get(user.getUsername());
    if (node != null) {
      List<User> friends = new ArrayList<>();
      for (SocialVertex friendNode : node.friends) {
        friends.add(friendNode.user);
      }
      return friends;
    }
    return new ArrayList<>();
  }

  /**
   * Gets all users in the social graph.
   *
   * @return a list of all users.
   */
//  public List<User> getAllUsers() {
//    List<User> allUsers = new ArrayList<>();
//    for (SocialVertex node : vertex.values()) {
//      allUsers.add(node.user);
//    }
//    return allUsers;
//  }
  // Dùng BFS để truyền thành 1 list
  public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Set<SocialVertex> visited = new HashSet<>();
        Queue<SocialVertex> queue = new LinkedList<>();

        // Bắt đầu từ tất cả các đỉnh (vì đồ thị có thể không liên thông)
        for (SocialVertex node : vertex.values()) {
            if (!visited.contains(node)) {
                queue.add(node);
                visited.add(node);

                while (!queue.isEmpty()) {
                    SocialVertex current = queue.poll();
                    allUsers.add(current.user);

                    for (SocialVertex neighbor : current.friends) {
                        if (!visited.contains(neighbor)) {
                            queue.add(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }

        return allUsers;
    }
  
  
}
