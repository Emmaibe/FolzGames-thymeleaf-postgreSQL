package org.lordibe.folzgames.services;

import org.lordibe.folzgames.entities.User;

public interface UserService {
    public User registerUser(String first_name,
                             String last_name,
                             String email,
                             String password,
                             String phone);

    public Boolean loginAuthentication(String email, String password);
}
