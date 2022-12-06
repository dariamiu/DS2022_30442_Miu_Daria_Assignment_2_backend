package ds.entity_utility_platform.controller;

import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ManageDeviceController {

    @Autowired
    DeviceService deviceService;

    @PutMapping("/devices/update")
    HashMap<String, Object> updateDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.updateDevice(deviceDTO);
    }

    @DeleteMapping("/devices/delete/{currentId}")
    HashMap<String, Object> deleteDevice(@PathVariable int currentId) {
        return deviceService.deleteDevice(currentId);
    }
}
