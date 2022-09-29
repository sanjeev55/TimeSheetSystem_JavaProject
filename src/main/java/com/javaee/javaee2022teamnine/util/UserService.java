package com.javaee.javaee2022teamnine.util;

import com.javaee.javaee2022teamnine.model.User;

import java.util.List;

public interface UserService {
    /**
     * @return Fetches all the User objects from users table
     */
    List<User> getAllUsers();

    /**
     * @param id User id
     * @return User object according to the user id provided
     */
    User getUserById(int id);

    /**
     * @param existingUser Existing user object
     * @param hasContract  Default false
     * @return When contract is created, 'hasContract' will be updated to true
     */
    boolean updateUserContractStatus(User existingUser, boolean hasContract);

    String generateMD5(String s);
}
