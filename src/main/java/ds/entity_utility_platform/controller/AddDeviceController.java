package ds.entity_utility_platform.controller;

import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AddDeviceController {

    @Autowired
    DeviceService deviceService;

    @PostMapping( "/device/add")
    HashMap<String, Object> addDevice(@RequestBody DeviceDTO device){
        return deviceService.addDevice(device);
    }
}
