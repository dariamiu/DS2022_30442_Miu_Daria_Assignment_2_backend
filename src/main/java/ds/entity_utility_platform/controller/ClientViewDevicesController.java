package ds.entity_utility_platform.controller;

import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClientViewDevicesController {
    @Autowired
    DeviceService deviceService;

    @GetMapping("/client/devices/view/{username}")
    public List<DeviceDTO> findDevicesByClient(@PathVariable String username){
        return deviceService.findDevicesByClient(username);
    }
}
