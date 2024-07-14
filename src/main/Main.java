package main;

import bo.UserBo;
import constant.IConstant;
import graph.SocialNetwork;
import java.util.List;
import mock.Data;
import model.User;
import util.Validate;

public class Main {

    public static void main(String[] args) {
        SocialNetwork socialNetwork = Data.socialNetwork;
        UserBo userBo = new UserBo(socialNetwork);
        String username;
        List<User> allUsers = socialNetwork.getAllUsers();

        while (true) {
            menu();
            int choice = Validate.getInt(
                    "Enter your choice [1-6]: ",
                    "Choice must be in [1-6].",
                    "Choice must be a digit!",
                    1, 6
            );

            if (choice == 6) {
                System.out.println("Exiting the application. Goodbye!");
                return;
            }

            switch (choice) {
                case 1:
                    displayAllUsers();
                    break;
                case 2:
                    username = Validate.getString(
                            "Enter username: ",
                            "Username is invalidated!",
                            IConstant.REGEX_USER_NAME
                    );
                    if (!userBo.isExist(username)) {
                        System.out.println("User " + username + " does not exist in the system.");
                        continue;
                    }
                    int maxRecommendations = Validate.getInt(
                            "Enter maximum number of recommendations: ",
                            "Number must be positive!",
                            "It must be a digit!",
                            0, Integer.MAX_VALUE
                    );

                    testFriendRecommendations(userBo, username, maxRecommendations);
                    break;
                case 3:
                    username = Validate.getString(
                            "Enter username: ",
                            "Username is invalidated!",
                            IConstant.REGEX_USER_NAME
                    );
                    if (!userBo.isExist(username)) {
                        System.out.println("User " + username + " does not exist in the system.");
                        continue;
                    }
                    testFriendList(userBo, username);
                    break;
                case 4:
                    username = Validate.getString(
                            "Enter username: ",
                            "Username is invalidated!",
                            IConstant.REGEX_USER_NAME
                    );
                    testAddNew(userBo, username);
                    break;
                case 5:
                    remove(allUsers);
                    
                    break;
            }
        }
    }

    private static void menu() {
        System.out.println("\nSocial Network Menu:");
        System.out.println("1. Show list User");
        System.out.println("2. Get Friend Recommendations");
        System.out.println("3. Get Friend List");
        System.out.println("4. Add a new User");
        System.out.println("5. Remove a User");
        System.out.println("6. Exit");
    }

    private static void testFriendRecommendations(UserBo userBo,
            String username,
            int maxRecommendations) {
        List<User> recommendations;
        recommendations = userBo.getFriendRecommendations(username, maxRecommendations);
        System.out.println("");
        System.out.println("-----Friend recommendations for " + username + "--------");

        for (User recommendation : recommendations) {
            System.out.println("- " + recommendation.getUsername());
        }

        System.out.println("-------------------------------------------");
    }

    private static void testFriendList(UserBo userBo, String username) {
        List<User> friends = userBo.getFriendList(username);

        System.out.println("\n--------Friends of " + username + "--------");
        for (User friend : friends) {
            System.out.println("- " + friend.getUsername());
        }
        System.out.println("------------------------------");
    }

    private static void testAddNew(UserBo userBo, String username) {
        Data.addNewUser(username);
    }

    public static void displayAllUsers() {
        Data.getAllUser();
    }
    public static void remove(List<User> allUsers ) {
        Data.removeUser(allUsers);
    }
}
