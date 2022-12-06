package ds.entity_utility_platform.service;

import ds.entity_utility_platform.dto.DailyConsumptionDTO;
import ds.entity_utility_platform.dto.HourlyConsumptionDTO;
import ds.entity_utility_platform.dto.builders.ObjectDTOBuilder;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.entity.HourlyConsumption;
import ds.entity_utility_platform.repository.DeviceRepository;
import ds.entity_utility_platform.repository.HourlyConsumptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
public class HourlyConsumptionService {

    @Autowired
    HourlyConsumptionRepository hourlyConsumptionRepository;

    @Autowired
    DeviceRepository deviceRepository;

    public List<HourlyConsumptionDTO> findDailyConsumptionByDevice(DailyConsumptionDTO dailyConsumptionDTO){
        List<HourlyConsumptionDTO> hourlyConsumptionDTOS = new ArrayList<>();
        Device device = deviceRepository.findDeviceByMyId(dailyConsumptionDTO.getIdDevice());

        List<HourlyConsumption> hourlyConsumptions = hourlyConsumptionRepository.findAllByDevice(device);

        if (hourlyConsumptions.isEmpty()){
            hourlyConsumptionDTOS = null;
        } else {
            ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();

            for (HourlyConsumption hourlyConsumption : hourlyConsumptions) {
                LocalDate localDate = LocalDate.parse(dailyConsumptionDTO.getDate());
                if(hourlyConsumption.getTimestamp().toLocalDate().isEqual(localDate)) {
                    hourlyConsumptionDTOS.add((HourlyConsumptionDTO) objectDTOBuilder.entityToDTO(hourlyConsumption));
                }
            }
        }
        return hourlyConsumptionDTOS;
    }

    public float findMaxConsumptionByDevice(Integer deviceId){
        Device device = deviceRepository.findDeviceByMyId(deviceId);

        List<HourlyConsumption> hourlyConsumptions = hourlyConsumptionRepository.findAllByDevice(device);

        if (hourlyConsumptions.isEmpty()){
            return 0.0f;
        } else {
            return hourlyConsumptions.stream().max(Comparator.comparing(HourlyConsumption::getConsumption)).get().getConsumption();
        }
    }

    public void introduceData(){
        List<Device> devices = deviceRepository.findAll();
        for (Device device1 : devices) {
            for (int i = 0; i <= 23; i++) {
                HourlyConsumption hourlyConsumption = new HourlyConsumption();
                hourlyConsumption.setDevice(device1);
                hourlyConsumption.setTimestamp(LocalDateTime.of(2022,11,5,i,0));
                Random r = new Random();
                float random = 0.0f + r.nextFloat() * (2 - 0.0f);
                hourlyConsumption.setConsumption(random);
                hourlyConsumptionRepository.save(hourlyConsumption);
            }
        }

    }
}
