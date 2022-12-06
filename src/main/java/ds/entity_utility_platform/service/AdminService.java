package ds.entity_utility_platform.service;

import ds.entity_utility_platform.dto.AdminDTO;
import ds.entity_utility_platform.entity.Admin;
import ds.entity_utility_platform.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    Logger logger = LoggerFactory.getLogger(AdminService.class);

    public HashMap<String, Object> saveAdmin(AdminDTO admin){

        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            Admin validAdmin = adminRepository.findByUsername(admin.getUsername());
            if (validAdmin != null) {
                response.put("message", "The username "+ admin.getUsername() +" is taken ");
                response.put("success", false);
                return response;
            } else {
                String encodedPassword = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
                adminRepository.save(new Admin(admin.getUsername(),encodedPassword));
                response.put("message", "Successful save");
                response.put("success", true);
                logger.info("A new admin has been created with the username : {}",admin.getUsername());
                return response;
            }


        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }

    public HashMap<String, Object> loginAdmin(AdminDTO adminDTO){

        Admin adminFromDB = adminRepository.findByUsername(adminDTO.getUsername());
        HashMap<String, Object> response = new HashMap<String, Object>();
        try {
            if (adminFromDB == null) {
                response.put("message", "No user with this username");
                response.put("success", false);
                return response;
            }
            else if (!BCrypt.checkpw(adminDTO.getPassword(),adminFromDB.getPassword())) {
                response.put("message", "Wrong password");
                response.put("success", false);
                return response;
            } else {
                response.put("message", "Successful login");
                response.put("success", true);
                logger.info("Admin with username {} logged in", adminDTO.getUsername());
                return response;
            }

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }

    }
}
