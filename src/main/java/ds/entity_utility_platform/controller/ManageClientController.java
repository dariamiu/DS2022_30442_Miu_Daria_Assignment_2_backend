package ds.entity_utility_platform.controller;


import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.dto.UpdateClientDTO;
import ds.entity_utility_platform.service.ClientService;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ManageClientController {
    @Autowired
    ClientService clientService;

    @PutMapping("/clients/update")
    HashMap<String, Object> updateDevice(@RequestBody UpdateClientDTO clientDTO) {
        System.out.println(clientDTO.getOldUsername());
        System.out.println(clientDTO.getNewUsername());
        System.out.println(clientDTO.getPasswordChanged());
        System.out.println(clientDTO.getPassword());
        return clientService.updateClient(clientDTO);
    }

    @DeleteMapping("/clients/delete/{username}")
    HashMap<String, Object> deleteDevice(@PathVariable String username) {
        return clientService.deleteClient(username);
    }
}
