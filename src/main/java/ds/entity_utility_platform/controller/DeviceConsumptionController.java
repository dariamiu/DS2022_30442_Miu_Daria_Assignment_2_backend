package ds.entity_utility_platform.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ds.entity_utility_platform.dto.DailyConsumptionDTO;
import ds.entity_utility_platform.dto.HourlyConsumptionDTO;
import ds.entity_utility_platform.service.DeviceService;
import ds.entity_utility_platform.service.HourlyConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceConsumptionController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    HourlyConsumptionService hourlyConsumptionService;

    @PostMapping("introduce-hourly-consumption")
    public void introduceData(){
        hourlyConsumptionService.introduceData();
        deviceService.updateMaxConsumption();
    }

    @GetMapping("/device/daily-consumption/{dailyConsumption}")
    public List<HourlyConsumptionDTO> getDailyConsumption(@PathVariable String dailyConsumption) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return hourlyConsumptionService.findDailyConsumptionByDevice(objectMapper.readValue(dailyConsumption, DailyConsumptionDTO.class));
    }

}
