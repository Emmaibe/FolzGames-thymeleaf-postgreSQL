package org.lordibe.folzgames.services;

import org.lordibe.folzgames.entities.Admin;

public interface AdminService {
    public Admin registerAdmin(String first_name,
                              String last_name,
                              String email,
                              String password,
                              String phone);

    public Boolean loginAuthentication(String email, String password);
}
