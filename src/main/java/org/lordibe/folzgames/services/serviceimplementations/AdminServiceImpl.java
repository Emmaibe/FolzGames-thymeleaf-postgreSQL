package org.lordibe.folzgames.services.serviceimplementations;

import jakarta.servlet.http.HttpSession;
import org.lordibe.folzgames.entities.Admin;
import org.lordibe.folzgames.repositries.AdminRepository;
import org.lordibe.folzgames.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private HttpSession session;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository, HttpSession session) {
        this.adminRepository = adminRepository;
        this.session = session;
    }


    @Override
    public Admin registerAdmin(String first_name,
                              String last_name,
                              String email,
                              String password,
                              String phone) {
        if (email == null || password == null) {
            return null;
        } else if (adminRepository.findFirstByEmail(email).isPresent()) {
            return null;
        } else {
            Admin admin = new Admin();

            admin.setFirstname(first_name);
            admin.setLastname(last_name);
            admin.setEmail(email);
            admin.setPassword(password);
            admin.setPhone(phone);

            adminRepository.save(admin);

            return admin;
        }
    }

    @Override
    public Boolean loginAuthentication(String email, String password) {
        Boolean isPresent = adminRepository.findByEmailAndPassword(email, password).isPresent();

        if (isPresent) {
            session.setAttribute("client", adminRepository.findFirstByEmail(email).orElse(null));
            return true;
        } else {
            return false;
        }
    }
}
