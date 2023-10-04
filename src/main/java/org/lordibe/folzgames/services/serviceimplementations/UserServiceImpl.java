package org.lordibe.folzgames.services.serviceimplementations;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.User;
import org.lordibe.folzgames.repositries.UserRepository;
import org.lordibe.folzgames.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private HttpSession session;
    private User user;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, HttpSession session) {
        this.session = session;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String first_name,
                             String last_name,
                             String email,
                             String password,
                             String phone) {
        if (email == null || password == null) {
            return null;
        } else if (userRepository.findFirstByEmail(email).isPresent()) {
            return null;
        } else {
            user = new User();

            user.setFirstname(first_name);
            user.setLastname(last_name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);

            userRepository.save(user);

            return user;
        }
    }

    @Override
    public Boolean loginAuthentication(String email, String password) {
        Boolean isPresent = userRepository.findByEmailAndPassword(email, password).isPresent();

        if (isPresent) {
            session.setAttribute("client", userRepository.findFirstByEmail(email).orElse(null));
            return true;
        } else {
            return false;
        }
    }
}
