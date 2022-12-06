package ds.entity_utility_platform.controller;


import ds.entity_utility_platform.dto.ClientDTO;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.service.ClientService;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddClientController {

    @Autowired
    ClientService clientService;

    @PostMapping( "/client/add")
    HashMap<String, Object> addCustomer(@RequestBody ClientDTO customer){
        return clientService.saveCustomer(customer);
    }

}
