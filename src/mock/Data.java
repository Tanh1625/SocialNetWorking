package mock;

import bo.UserBo;
import constant.IConstant;
import graph.SocialNetwork;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import model.User;
import util.Validate;

public class Data {

    public static List<User> users = new ArrayList<>();
    public static SocialNetwork socialNetwork = new SocialNetwork();

    static {
        // Create users
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");
        User david = new User("David");
        User eve = new User("Eve");
        User frank = new User("Frank");
        User grace = new User("Grace");
        User henry = new User("Henry");
        User isabel = new User("Isabel");
        User jack = new User("Jack");
        User kate = new User("Kate");
        User liam = new User("Liam");

        // Add users to the list
        users.add(alice);
        users.add(bob);
        users.add(charlie);
        users.add(david);
        users.add(eve);
        users.add(frank);
        users.add(grace);
        users.add(henry);
        users.add(isabel);
        users.add(jack);
        users.add(kate);
        users.add(liam);

        // Add users to the social network
        for (User user : users) {
            socialNetwork.addUser(user);
        }

        // Add friendships
        socialNetwork.addFriendship(alice, bob);
        socialNetwork.addFriendship(alice, charlie);
        socialNetwork.addFriendship(alice, david);
        socialNetwork.addFriendship(bob, charlie);
        socialNetwork.addFriendship(bob, eve);
        socialNetwork.addFriendship(charlie, frank);
        socialNetwork.addFriendship(david, eve);
        socialNetwork.addFriendship(david, frank);
        socialNetwork.addFriendship(eve, grace);
        socialNetwork.addFriendship(frank, grace);
        socialNetwork.addFriendship(grace, henry);
        socialNetwork.addFriendship(henry, isabel);
        socialNetwork.addFriendship(isabel, jack);
        socialNetwork.addFriendship(jack, kate);
        socialNetwork.addFriendship(kate, liam);
        socialNetwork.addFriendship(liam, alice);
        socialNetwork.addFriendship(bob, isabel);
        socialNetwork.addFriendship(charlie, kate);
        socialNetwork.addFriendship(david, jack);
        socialNetwork.addFriendship(eve, liam);
    }

    public static void addNewUser(String username) {
        List<User> allUsers = socialNetwork.getAllUsers();
        Scanner sc = new Scanner(System.in);

        User user = new User(username);

        UserBo ub = new UserBo(socialNetwork);
        if (ub.isExist(username)) {
            System.out.println("User already exists ");
            return;
        }

        users.add(user);
        socialNetwork.addUser(user);

        //System.out.println("Enter the number of friendship: ");
        int fs = Validate.getInt(
                "Enter the number of friendship: ",
                "Choice must be in [1-10]",
                "Choice must be a digit!",
                1, 10);

        //Hien thi danh sach 
        System.out.println("\n-----Existing list-----");
        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println("User " + (i + 1) + allUsers.get(i));
        }
        System.out.println("_______________________");
        System.out.println("");
        while (fs > 0) {
            System.out.println("Enter the name of his/her friend: ");
            String friendName = sc.nextLine();
            User friend = new User(friendName);
//            if (!allUsers.contains(friend)) {
//                socialNetwork.addUser(friend);
//            }
            boolean check = true;
            for (int i = 0; i < allUsers.size(); i++) {
                if (!allUsers.get(i).getUsername().equalsIgnoreCase(friendName)) {
                    check = false;
                } else {
                    check = true;
                    break;
                }
            }
            if (!check) {
                socialNetwork.addUser(friend);
            }

            socialNetwork.addFriendship(user, friend);
            fs--;

        }

        System.out.println("-----Add complete-----");
    }

    public static void getAllUser() {
        System.out.println("");
        System.out.println("----List User----");
        List<User> allUsers = socialNetwork.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println("User " + (i + 1) + allUsers.get(i).toString());
        }
        System.out.println("----------------");
    }

    public static List<User> removeUser(List<User> listRemove) {
        String username = "";
        username = Validate.getString(
                "Enter username: ",
                "Username is invalidated!",
                IConstant.REGEX_USER_NAME
        );
        List<User> allUsers = socialNetwork.getAllUsers();

        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).getUsername().equalsIgnoreCase(username)) {
                allUsers.remove(i);
            }
        }
        System.out.println("\n----List after deletion----");
        for (int i = 0; i < allUsers.size(); i++) {
            System.out.println("user " + (i + 1) + allUsers.get(i));

        }
        System.out.println("-------------------------");
        listRemove = allUsers;
        return listRemove;

    }

}
