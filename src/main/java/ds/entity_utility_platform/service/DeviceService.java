package ds.entity_utility_platform.service;

import ds.entity_utility_platform.dto.DeviceDTO;
import ds.entity_utility_platform.dto.builders.ObjectDTOBuilder;
import ds.entity_utility_platform.entity.Admin;
import ds.entity_utility_platform.entity.Client;
import ds.entity_utility_platform.entity.Device;
import ds.entity_utility_platform.entity.HourlyConsumption;
import ds.entity_utility_platform.repository.ClientRepository;
import ds.entity_utility_platform.repository.DeviceRepository;
import ds.entity_utility_platform.repository.HourlyConsumptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    HourlyConsumptionService hourlyConsumptionService;

    Logger logger = LoggerFactory.getLogger(DeviceService.class);

    public HashMap<String,Object> addDevice(DeviceDTO deviceDTO){

        HashMap<String, Object> response = new HashMap<String, Object>();

        Client client = clientRepository.findByUsername(deviceDTO.getClientName());

        try{
            ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();
            Device device = objectDTOBuilder.deviceDTOToEntity(client, deviceDTO.getDescription(),
                    deviceDTO.getMax_h_cons(),deviceDTO.getAddress());
            deviceRepository.save(device);

            response.put("message", "Device created!");
            response.put("success", true);
            return response;

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            return response;
        }
    }

    public List<DeviceDTO> findAll() {
        List<Device> devices = deviceRepository.findAll();
        if(devices.isEmpty()){
            logger.warn("There are no devices in the database");
        }

        List<DeviceDTO> deviceDTOS = new ArrayList<>();

        ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();

        for (Device device : devices) {
            deviceDTOS.add((DeviceDTO) objectDTOBuilder.entityToDTO(device));
        }

        return deviceDTOS;
    }

    public HashMap<String,Object> updateDevice(DeviceDTO deviceDTO){
        HashMap<String,Object> response = new HashMap<>();

        try {
            Device device = deviceRepository.findDeviceByMyId(deviceDTO.getId_device());
            device.setAddress(deviceDTO.getAddress());
            device.setDescription(deviceDTO.getDescription());
            device.setMax_h_cons(deviceDTO.getMax_h_cons());
            Client client = clientRepository.findByUsername(deviceDTO.getClientName());
            if (client == null){
                response.put("message","Username does not exist");
                response.put("success", false);
            } else {
                device.setClient(client);
                deviceRepository.save(device);
                response.put("message","Device Changed");
                response.put("success", true);
            }

        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }
        return response;
    }

    public HashMap<String,Object> deleteDevice( int currentId ){
        HashMap<String,Object> response = new HashMap<>();
        try{
            Device device = deviceRepository.findDeviceByMyId(currentId);
            deviceRepository.delete(device);
            response.put("message","Device Deleted");
            response.put("success", true);
        } catch (Exception e) {
            response.put("message", e.getMessage());
            response.put("success",false);
            logger.error(e.getMessage());
            return response;
        }
        return response;
    }

    public List<DeviceDTO> findDevicesByClient(String username){
        Client client = clientRepository.findByUsername(username);
        System.out.println(username);
        System.out.println(client.getUsername());
        List<Device> devices = deviceRepository.findAllByClient(client);
        System.out.println(devices.size());

        ObjectDTOBuilder objectDTOBuilder = new ObjectDTOBuilder();
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        if(devices.isEmpty()) {
            deviceDTOS = null;
        } else {
            for (Device device : devices) {
                deviceDTOS.add((DeviceDTO) objectDTOBuilder.entityToDTO(device));
            }
        }
        return deviceDTOS;
    }

    public void updateMaxConsumption() {
        List<Device> devices = deviceRepository.findAll();
        for (Device device : devices) {
            device.setMax_h_cons(hourlyConsumptionService.findMaxConsumptionByDevice(device.getId_device()));
            deviceRepository.save(device);
        }
    }

    public String getUser(Device device){
        return device.getClient().getUsername();
    }
}