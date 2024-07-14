package graph;

import model.User;
import java.util.HashSet;
import java.util.Set;

class SocialVertex {

  User user;
  Set<SocialVertex> friends;

  public SocialVertex(User user) {
    this.user = user;
    this.friends = new HashSet<>();
  }

  /**
   * Adds a friend to this node's friend list.
   *
   * @param friend the SocialNode representing the friend to be added.
   */
  public void addFriend(SocialVertex friend) {
    friends.add(friend);
  }
}
