package ds.entity_utility_platform.controller;

import ds.entity_utility_platform.dto.AdminDTO;
import ds.entity_utility_platform.dto.ClientDTO;
import ds.entity_utility_platform.entity.Admin;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.service.AdminService;
import ds.entity_utility_platform.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    ClientService customerService;

    @Autowired
    AdminService adminService;

    @PostMapping( "/client/login")
    HashMap<String, Object> loginCustomer(@RequestBody ClientDTO customer){
        return customerService.loginCustomer(customer);
    }

    @PostMapping("/admin/login")
    HashMap<String, Object> loginAdmin(@RequestBody AdminDTO admin){
        return adminService.loginAdmin(admin);

    }

    @PostMapping("/admin/register")
    HashMap<String,Object> registerAdmin(@RequestBody AdminDTO adminDTO){
        return  adminService.saveAdmin(adminDTO);
    }
}
